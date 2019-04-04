import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/test2'
        headers {
            header('''Content-Type''', '''application/json;charset=UTF-8''')
            header('''Auth''', '''abc''')
        }
    }
    response {
        status 200
        body('''Hello, is it me you're looking for?''')
        headers {
            header('''Content-Type''', '''text/plain;charset=UTF-8''')
        }
    }
}