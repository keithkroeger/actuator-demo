import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/test2'
    }
    response {
        status 200
        body("Hello, is it me you're looking for?")
    }
}