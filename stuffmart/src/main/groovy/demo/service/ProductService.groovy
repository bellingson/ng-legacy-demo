package demo.service

import org.springframework.stereotype.Service

@Service
class ProductService {

    final static String DESCRIPTION = 'Bacon ipsum dolor amet tenderloin pork belly chicken spare ribs pancetta rump hamburger turducken jerky burgdoggen. Tenderloin ham hock alcatra, biltong leberkas frankfurter short ribs tri-tip. Meatball tenderloin beef ribs ground round turkey bresaola capicola, flank fatback shank pork ribeye beef kevin. Ham hock biltong pork belly corned beef, shank kevin burgdoggen pork chop.'

    private List products = []

    ProductService() {
        products << [id: 1, name: 'Tiffany Clock', price: 1000.00, img: '/img/clock.jpg', description: DESCRIPTION ]
        products << [id: 2, name: 'Super Car', price: 200000.00, img: '/img/car.jpg', description: DESCRIPTION ]
        products << [id: 3, name: 'Big Gulp', price: 7.99, img: '/img/wine.jpg', description: DESCRIPTION ]
    }

    List list() {
        return products
    }

    Map get(Long id) {
        return products.find { it.id == id }
    }


    void add(Map params) {

        params.id = products.collect { it.id }.max() + 1
        params.img = '/img/placeholder.jpg'
        params.description = DESCRIPTION
        products << params

    }


    void update(Long id, Map params) {

        Map product = products.find { it.id == id }
        if(product) {

            params.each { k, v ->
                product[k] = v
            }
        }

    }

    void delete(Long id) {

        Map product = products.find { it.id == id }
        if (product) {
            products.remove(product)
        }

    }


}
