package demo

class AddTransformer implements Transform {

    @Override
    def transform(def args) {
        if (args instanceof List) {
            return args.sum()
        }
    }

}
