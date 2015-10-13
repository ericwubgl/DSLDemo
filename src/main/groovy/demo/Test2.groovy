package demo
//def email(@DelegatesTo(EmailSpec) Closure cl) {
//    def email = new EmailSpec()
//    def code = cl.rehydrate(email, this, this)
//    code.resolveStrategy = Closure.DELEGATE_ONLY
//    code()
//}
//
//class EmailSpec {
//    void from(String from) { println "From: $from"}
//    void to(String... to) { println "To: $to"}
//    void subject(String subject) { println "Subject: $subject"}
//    def methodMissing(String methodName, args) {
//        println methodName
//        println args
//    }
//}
//
//email {
//    from 'dsl-guru@mycompany.com'
//    to 'john.doe@waitaminute.com', 'sdb@asd.com'
//    subject 'The pope has resigned!'
//    haha null
//}

def transform(@DelegatesTo.Target List elements, @DelegatesTo(Transform) Closure cl) {
    def trans = new Transform(elements)
    def code = cl.rehydrate(trans, this, this)
    code.resolveStrategy = Closure.DELEGATE_ONLY
    code()
}

class Transform {
    def values = []
    def elements
    Transform(List elements) {
        this.elements = elements
    }
    void from(Integer... from) {
        from.each {
            values << elements[it-1]
        }
    }
    def add() {
        values.sum()
    }
}

def samples = [1,2,3,4,5,6,7,8,9]
def result = transform(samples) {
    from 1, 3, 4
    add()
}
println result