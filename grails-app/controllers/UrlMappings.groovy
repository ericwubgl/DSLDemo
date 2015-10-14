class UrlMappings {

    static mappings = {
        group("/api/demo") {
            "/$action?/$inputParam?"(controller: "demo")
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
