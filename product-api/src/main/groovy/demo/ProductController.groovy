package demo

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    final static Map OK = [message: 'ok']

    List products = []

    ProductController() {
        products << [id: 1, name: 'Tiffany Lamp', price: 1000.00 ]
        products << [id: 2, name: 'Super Car', price: 200000.00 ]
        products << [id: 3, name: 'Big Gulp', price: 7.99 ]
    }

    @RequestMapping(value="/api/admin/product", method=RequestMethod.GET)
    @ResponseBody List list() {
        return products
    }

    @RequestMapping(value="/api/admin/product/{id}", method=RequestMethod.GET)
    @ResponseBody Map get(@PathVariable('id') Long id) {
        return products.find { it.id == id }
    }

    @RequestMapping(value="/api/admin/product", method=RequestMethod.POST)
    @ResponseBody Map add(@RequestBody Map params) {

        params.id = products.collect { it.id }.max() + 1
        products << params

       OK
    }

    @RequestMapping(value="/api/admin/product/{id}", method=RequestMethod.PUT)
    @ResponseBody Map update(@PathVariable('id') Long id, @RequestBody Map params) {

        Map product = products.find { it.id == id }
        if(product) {

            params.each { k, v ->
                product[k] = v
            }
        }

        OK
    }

    @RequestMapping(value="/api/admin/product/{id}", method=RequestMethod.DELETE)
    @ResponseBody Map delete(@PathVariable('id') Long id) {

        Map product = products.find { it.id == id }
        if(product) {
            products.remove(product)
         }

        OK
    }

}
