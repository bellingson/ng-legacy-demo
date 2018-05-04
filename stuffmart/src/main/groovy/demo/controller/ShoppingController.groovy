package demo.controller

import demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ShoppingController {

    @Autowired
    ProductService productService

    @RequestMapping(value=['/','/store'], method=RequestMethod.GET)
    String store(Model model) {

        model.products = productService.list()

        'store'
    }

    @RequestMapping(value=['/product/{id}'], method=RequestMethod.GET)
    String store(Model model, @PathVariable Long id) {

        model.product = productService.get(id)

        'product'
    }

}
