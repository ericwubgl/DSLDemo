package demo

class DemoController {

    DataLoader dataLoader

    def demo() {
        def body = request.JSON.body
        dataLoader.load(body)
        render "OK"
    }

}
