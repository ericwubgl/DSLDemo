package demo

class FileType {

    def values = []
    def elements

    FileType(List elements) {
        this.elements = elements
    }

    def methodMissing(String methodName, args) {
        println "Mapping field ${methodName}"
        def output
        if (args[0] instanceof Closure) {
            Closure cl = (Closure) args[0]
            def field = new Field(elements)
            def code = cl.rehydrate(field, this, this)
            code.resolveStrategy = Closure.DELEGATE_ONLY
            code()
            output = field.output
        }
        println "Setting field ${methodName} value: ${output}"
    }

}
