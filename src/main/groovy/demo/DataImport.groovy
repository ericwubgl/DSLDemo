package demo

class DataImport {

    def elements

    DataImport(List elements) {
        this.elements = elements
    }

    def methodMissing(String methodName, args) {
        println "Importing file for ${methodName}"
        if (args[0] instanceof Closure) {
            Closure cl = (Closure) args[0]
            def fileType = new FileType(elements)
            def code = cl.rehydrate(fileType, this, this)
            code.resolveStrategy = Closure.DELEGATE_ONLY
            code()
        }
    }

}
