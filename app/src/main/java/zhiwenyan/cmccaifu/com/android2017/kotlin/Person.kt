package chapter05

/**
 * Description:
 * Data：9/21/2018-11:57 AM
 * @author: yanzhiwen
 */
class Person {
    var name = "steven"

        set(value) {
            this.name = field

        }
        get() {
            return name
        }

    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return "Person(name='$name')"

    }

}
