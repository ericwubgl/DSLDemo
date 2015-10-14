package demo

abstract class DataImport {

    def elements

    def methodMissing(String methodName, args) {
        println "Mapping field ${methodName}"
        def output
        if (args[0] instanceof Closure) {
            Closure cl = (Closure) args[0]
            Field field = createField()
            field.elements = elements
            def code = cl.rehydrate(field, this, this)
            code.resolveStrategy = Closure.DELEGATE_ONLY
            code()
            output = field.output
        }
        println "Setting field ${methodName} value: ${output}"
    }

    protected abstract createField()

}
