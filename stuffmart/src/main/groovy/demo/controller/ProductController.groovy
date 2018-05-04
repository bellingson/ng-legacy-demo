package demo.controller

import demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    final static Map OK = [message: 'ok']

    @Autowired
    ProductService productService

    @RequestMapping(value="/api/admin/product", method=RequestMethod.GET)
    @ResponseBody List list() {
        return productService.list()
    }

    @RequestMapping(value="/api/admin/product/{id}", method=RequestMethod.GET)
    @ResponseBody Map get(@PathVariable('id') Long id) {
        return productService.get(id)
    }

    @RequestMapping(value="/api/admin/product", method=RequestMethod.POST)
    @ResponseBody Map add(@RequestBody Map params) {

        productService.add(params)

       OK
    }

    @RequestMapping(value="/api/admin/product/{id}", method=RequestMethod.PUT)
    @ResponseBody Map update(@PathVariable('id') Long id, @RequestBody Map params) {

        productService.update(id, params)
        OK
    }

    @RequestMapping(value="/api/admin/product/{id}", method=RequestMethod.DELETE)
    @ResponseBody Map delete(@PathVariable('id') Long id) {

        productService.delete(id)

        OK
    }

    
}
