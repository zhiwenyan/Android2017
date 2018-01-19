package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain;

/**
 * Description:
 * Data：1/16/2018-1:53 PM
 *
 * @author: yanzhiwen
 */
//职责链模式的核心在于抽象处理者类的设计
abstract class Handler {
    //维持对下家的引用
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(String request);
}

class ConcreteHandler extends Handler {
    public void handleRequest(String request) {
        //true  满足条件
        if (true) {
            //处理请求
        } else {
            //转发请求
            this.successor.handleRequest(request);
        }
    }
}