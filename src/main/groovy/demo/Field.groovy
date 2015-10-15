package demo

class Field {

    def values = []
    def elements
    def output
    Transformer transformer

    void from(Integer... from) {
        if (elements == null) {
            return
        }
        if (from.length == 1) {
            output = elements[from[0]-1]
            return
        }
        from.each {
            values << elements[it-1]
        }
        println "From these values: ${values}"
    }

    void transform(def arg) {
        println "Transforming, calling plugin ${arg}"
        output = transformer.transform(arg, output == null ? values : output)
        println "Output: ${output}"
    }

}
