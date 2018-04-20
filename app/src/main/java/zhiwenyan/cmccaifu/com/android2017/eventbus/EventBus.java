package zhiwenyan.cmccaifu.com.android2017.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 * Data：4/9/2018-4:14 PM
 *
 * @author: yanzhiwen
 */
public class EventBus {
    private static volatile EventBus defaultEventBus;

    /**
     * subscriptionsByEventType这个集合存放的是？
     * key是Event参数的类
     * value是存放的是Subscription集合列表
     * Subscription 包含了两个属性，一个是subscriber订阅者（反射执行的对象），一个是SubscriberMethod注解方法的所有参数的属性值
     */
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    /**
     * typesBySubscriber这个集合存放的是？
     * key是所有的订阅者
     * value是所有订阅者里面的方法的参数的class
     */
    private final Map<Object, List<Class<?>>> typesBySubscriber;

    private EventBus() {
        typesBySubscriber = new HashMap<>();
        subscriptionsByEventType = new HashMap<>();
    }

    public static EventBus getDefault() {
        if (defaultEventBus == null) {
            synchronized (EventBus.class) {
                if (defaultEventBus == null) {
                    defaultEventBus = new EventBus();
                }
            }
        }
        return defaultEventBus;
    }

    /**
     * @param subscriber 订阅者
     */
    public void register(Object subscriber) {
        List<SubscriberMethod> subscriberMethods = new ArrayList<>();
        //1、解析所有方法，封装成SubscriberMethod的集合
        Class<?> objClass = subscriber.getClass();
        Method[] methods = objClass.getDeclaredMethods();
        for (Method method : methods) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (subscribe != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                SubscriberMethod subscriberMethod = new SubscriberMethod(method, parameterTypes[0],
                        subscribe.threadMode(), subscribe.priority(), subscribe.sticky());
                subscriberMethods.add(subscriberMethod);
            }
        }
        //2、按照规则存放到subscriptionsByEventType里面去
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            subscriber(objClass, subscriberMethod);

        }
    }


    private void subscriber(Class<?> objClass, SubscriberMethod subscriberMethod) {
        Class<?> eventType = subscriberMethod.eventType;
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions == null) {
            subscriptions = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(eventType, subscriptions);
        }
        //判断优先级（忽略）
        Subscription subscription = new Subscription(objClass, subscriberMethod);
        subscriptions.add(subscription);

        //typesBySubscriber是为了方便移除
        List<Class<?>> eventTypes = typesBySubscriber.get(objClass);
        if (eventTypes == null) {
            eventTypes = new ArrayList<>();
            typesBySubscriber.put(objClass, eventTypes);
        }
        if (!eventTypes.contains(eventType)) {
            eventTypes.add(eventType);
        }
    }

    public void unregister(Object subscriber) {
        List<Class<?>> eventTypes = typesBySubscriber.get(subscriber);
        if (eventTypes != null) {
            for (Class<?> eventType : eventTypes) {
                removeObject(eventType, subscriber);
            }
        }
    }

    private void removeObject(Class<?> eventType, Object subscriber) {
        List<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null) {
            int size = subscriptions.size();
            for (int i = 0; i < size; i++) {
                Subscription subscription = subscriptions.get(i);
                if (subscription.subscriber == subscriber) {
                    subscription.active = false;
                    subscriptions.remove(i);
                    i--;
                    size--;
                }
            }
        }
    }

    public void post(Object event) {
        Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null) {
            for (Subscription subscription : subscriptions) {
                subscription.executeMethod(subscription, event);
            }
        }
    }

}
