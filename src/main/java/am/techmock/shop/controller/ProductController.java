package am.techmock.shop.controller;

import am.techmock.shop.model.Flavour;
import am.techmock.shop.model.Product;
import am.techmock.shop.model.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class ProductController {

	@GetMapping("/products")
	public List<Product> products() {
		return List.of(
				Product.of(
						1, "Արևածաղիկ", "Թարմ և որակյալ արևածաղկի սերմեր",
						Resource.image("media/otborniy.png"), Resource.image("media/otborniy.png"), 400,
						List.of(
								Flavour.of(
										1, "Սովորական", "images/sunflower.png",
										"Պիտանիության ժամկետը՝ 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում, օդի 25 oC ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ։"
								),
								Flavour.of(
										2, "Աղի", "images/salty_sunflower.png",
										"Պիտանիության ժամկետը՝ 180 օր արտադրման օրից պահման պայմանների դեպքում։ Պահել չոր և զով վայրում օդի 25 oC ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420 կՋ։"
								)
						)
				)
		);
	}

}
