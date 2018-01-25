package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit;

import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple.RequestBuilder;

/**
 * Description:
 * Data：1/19/2018-3:57 PM
 *
 * @author: yanzhiwen
 */
public interface ParameterHandler<T> {
    void apply(RequestBuilder requestBuilder,T value);
    //很多策略 Query,Part,QueryMap,Filed等等

    class Query<T> implements ParameterHandler<T> {
        private String key;

        public Query(String key) {
            this.key = key;
        }

        @Override
        public void apply(RequestBuilder requestBuilder,T value) {
            //添加到Request中   value变成String需要一个工厂设计模式
            requestBuilder.addQueryName(key,value.toString());

        }
    }
}
