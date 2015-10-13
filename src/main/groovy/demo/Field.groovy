package demo

class Field {

    def values = []
    def elements
    def output

    Field(List elements) {
        this.elements = elements
    }

    void from(Integer... from) {
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
        Transformer transformer = new Transformer()
        output = transformer.transform(arg, values)
    }

}
