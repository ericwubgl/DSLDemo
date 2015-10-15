package demo

class AddOneTransformer implements Transform {

    @Override
    def transform(def args) {
        if (args instanceof List) {
            def list = []
            args.each {
                list << it + 1
            }
            return list
        }
    }

}
