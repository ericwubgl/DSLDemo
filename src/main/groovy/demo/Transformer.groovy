package demo

class Transformer {

    protected methods = [:]

    def add = { values ->
        values.sum()
    }

    def concat = { values ->
        String output = ""
        values.each { value ->
            output = "${output} ${value}"
        }
        output.trim()
    }

    Transformer() {
        methods << ['add': add]
        methods << ['concat': concat]
    }

    def transform(String methodName, args) {
        println "Getting method: ${methodName}"
        def method = methods[methodName]
        if (method instanceof Closure) {
            return method(args)
        }
    }

}
