
package zhiwenyan.cmccaifu.com.android2017.eventbus;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;

final class Subscription {
    final Object subscriber;
    final SubscriberMethod subscriberMethod;
    volatile boolean active;

    Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
        active = true;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Subscription) {
            Subscription otherSubscription = (Subscription) other;
            return subscriber == otherSubscription.subscriber
                    && subscriberMethod.equals(otherSubscription.subscriberMethod);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return subscriber.hashCode() + subscriberMethod.methodString.hashCode();
    }

    public void executeMethod(Subscription subscription, Object event) {
        ThreadMode threadMode = subscription.subscriberMethod.threadMode;
        //判断是否主线程
        boolean isMainThread = Looper.getMainLooper() == Looper.myLooper();
        switch (threadMode) {
            case POSTING:
                invokeMethod(subscription, event);
                break;
            case MAIN:
                if (isMainThread) {
                    invokeMethod(subscription, event);
                } else {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> invokeMethod(subscription, event));
                }
                break;
            case ASYNC:
                break;
            case BACKGROUND:
                if (!isMainThread) {
                    invokeMethod(subscription, event);
                } else {
                    //todo
                }
                break;

        }
    }

    private void invokeMethod(Subscription subscription, Object event) {
        try {
            subscriberMethod.method.invoke(subscription.subscriber, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}