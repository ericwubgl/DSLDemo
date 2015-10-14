package demo

class ConcatTransformer implements Transform {

    @Override
    def transform(def args) {
        String output = ""
        args.each { value ->
            output = "${output} ${value}"
        }
        output.trim()
    }

}
