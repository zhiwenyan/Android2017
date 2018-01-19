package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple5;

/**
 * Description:
 * Data：1/16/2018-2:04 PM
 *
 * @author: yanzhiwen
 */
//Sunny软件公司的OA系统需要提供一个假条审批模块：如果员工请假天数小于3天，主任可以审批该假条；
//如果员工请假天数大于等于3天，小于10天，经理可以审批；如果员工请假天数大于等于10天，小于30天，总经理可以审批；
// 如果超过30天，总经理也不能审批，提示相应的拒绝信息。试用职责链模式设计该假条审批模块。

/**
 * 请假请求类
 */
class leaveRequest {
    private int day;

    public leaveRequest(int day) {
        this.day = day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}

abstract class Approver {
    //下一个处理者
    protected Approver nextApprover;
    //处理者的姓名
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    protected abstract void processRequest(leaveRequest request);
}

class Director extends Approver {
    public Director(String name) {
        super(name);
    }

    @Override
    protected void processRequest(leaveRequest request) {
        if (request.getDay() < 3) {
            //处理假条
            System.out.println("如果员工请假天数小于3天，主任可以审批该假条");
        } else {
            this.nextApprover.processRequest(request);
        }
    }
}

class Manager extends Approver {
    public Manager(String name) {
        super(name);
    }

    @Override
    protected void processRequest(leaveRequest request) {
        if (request.getDay() >= 3 && request.getDay() < 10) {
            //处理假条
            System.out.println("如果员工请假天数大于等于3天，小于10天，经理可以审批");

        } else {
            this.nextApprover.processRequest(request);
        }
    }
}

class TopManager extends Approver {
    public TopManager(String name) {
        super(name);
    }

    @Override
    protected void processRequest(leaveRequest request) {
        if (request.getDay() >= 3 && request.getDay() < 30) {
            //处理假条
            System.out.println("如果员工请假天数大于等于10天，小于30天，总经理可以审批");
        } else {
            System.out.println("请假天数太多，直接被拒");
        }
    }
}

public class Client {
    public static void main(String[] args) {
        //相应的处理者
        Approver director = new Director("主任");
        Approver manager = new Manager("经理");
        Approver topManager = new TopManager("总经理");
        //下一个处理者
        director.setNextApprover(manager);
        manager.setNextApprover(topManager);

        leaveRequest request1 = new leaveRequest(1);
        leaveRequest request2 = new leaveRequest(5);
        leaveRequest request3 = new leaveRequest(15);
        leaveRequest request4 = new leaveRequest(20);
        leaveRequest request5 = new leaveRequest(35);

        director.processRequest(request1);
        director.processRequest(request2);
        director.processRequest(request3);
        director.processRequest(request4);
        director.processRequest(request5);
    }
}
