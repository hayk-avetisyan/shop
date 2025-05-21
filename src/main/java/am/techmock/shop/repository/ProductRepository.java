package am.techmock.shop.repository;

import am.techmock.shop.model.Product;
import am.techmock.shop.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Repository
public class ProductRepository {
	private final Map<Integer, ProductCategory> categories = new ConcurrentHashMap<>();
	private final AtomicInteger categoryIdGenerator = new AtomicInteger(9); // Start from 9 to avoid conflicts with existing IDs
	private final AtomicInteger productIdGenerator = new AtomicInteger(21); // Start from 21 to avoid conflicts with existing IDs

	public ProductRepository() {
		List<ProductCategory> initialCategories = List.of(
				ProductCategory.of(
						1, "Արևածաղկի սերմեր", "Թարմ և որակյալ արևածաղկի սերմեր",
						"media/otborniy.png", null,
						Map.of(
								1, Product.of(
										1, "Սովորական", "media/otborniy.png",
										"Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը՝ 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում, օդի  25˚C ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
										200
								),
								2, Product.of(
										2, "Բոված ծովի աղով", "media/arevacaxik-axi.png",
										"Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը՝ 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում, օդի  25˚C ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
										200
								),
								3, Product.of(
										3, "Գծավոր բոված սերմեր ծովի աղով", "media/gcavor axi.png",
										"Բաղադրությունը` արևածաղկի  սերմեր, աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում  օդի 25˚C ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 49.6 գ Ածխաջրեր – 19.5 գ Սպիտակուցներ – 22.8 գ Էներգետիկ արժեքը – 616 կկալ/2579կՋ",
										350
								),
								4, Product.of(
										4, "Գծավոր բոված սերմեր", "media/gcavor.png",
										"Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում  օդի 25˚C ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
										350
								),
								5, Product.of(
										5, "Աղով և առանց աղի միքս", "media/mix.png",
										"Բաղադրությունը` արևածաղկի  սերմեր, ծովի աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում օդի 25˚C  ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
										350
								),
								6, Product.of(
										6, "Սպիտակ բոված սերմեր ծովի աղով", "media/spitak-axi.png",
										"Բաղադրությունը` արևածաղկի  սերմեր, կերակրի աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում օդի 25 oC ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 34.5 գ Ածխաջրեր – 5.44 գ Սպիտակուցներ – 17.06 գ Էներգետիկ արժեքը – 386 կկալ/1659ԿՋ",
										350
								),
								7, Product.of(
										7, "«Մոլոդյոժնիե» հատընտիր բոված սերմեր", "media/molod.png",
										"Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում օդի 25˚C ջերմաստիճանից ոչ բարձր և 75 % ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/ 2421,82 կՋ",
										400
								),
								8, Product.of(
										8, "«Մոլոդյոժնիե» հատընտիր բոված սերմեր ծովի աղով", "media/molod-axi.png",
										"Բաղադրությունը` արևածաղկի  սերմեր, ծովի աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով  վայրում օդի 25˚C ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52,9 գ Ածխաջրեր – 5,0 գ գ Սպիտակուցներ – 20,7 գ Էներգետիկ արժեքը – 579կկալ/ 2424կՋ",
										400
								),
								9, Product.of(
										9, "«Օսոբեննիյե» հատընտիր բոված սերմեր ծովի աղով", "media/osob.jpg",
										"Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը` ոչ ավել, քան 350 օր արտադրման և փաթեթավորման օրից` պահման պայմանների դեպքում, օդի 25°C ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 578 կկալ/2394 կՋ",
										420
								),
								10, Product.of(
										10, "«Օսոբեննիյե» հատընտիր բոված սերմեր ծովի աղով", "media/osob-axi.png",
										"Բաղադրությունը` արևածաղկի  սերմեր, ծովի աղ: Պիտանիության ժամկետը` ոչ ավել, քան 350 օր արտադրման և փաթեթավորման օրից` պահման պայմանների դեպքում, օդի 25°C ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 578 կկալ/2394 կՋ",
										420
								)
						)
				),
				ProductCategory.of(
						2, "Չիպսեր", "Խրթխրթան և համեղ չիպսեր",
						"media/chips.jpg", "media/chips.mp4",
						Map.of(
								11, Product.of(
										11, "Դասական", "media/chips-dasakan.png",
										"Բաղադրությունը` կարտոֆիլի չոր պյուրե, ցորենի ալյուր, կարտոֆիլի օսլա, արևածաղկի ձեթ, խմելու ջուր, կերակրի աղ: Պիտանիության ժամկետը՝ 150 օր արտադրման օրից պահման պայմանների դեպքում: Պահել օդի 25˚C -ից ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր  — 35 գ Ածխաջրեր — 51 գ Սպիտակուցներ — 4,3 գ Էներգետիկ արժեքը — 536 կկալ/2244 կՋ",
										200
								),
								12, Product.of(
										12, "Թթվասերի", "media/chips-ttvaser.png",
										"Բաղադրությունը` կարտոֆիլի չոր պյուրե, ցորենի ալյուր, կարտոֆիլի օսլա, արևածաղկի ձեթ, խմելու ջուր, կերակրի աղ, համալիր համային հավելում «Թթվասեր»: Պիտանիության ժամկետը՝ 150 օր արտադրման օրից պահման պայմանների դեպքում: Պահել օդի 25˚C -ից ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր — 35 գ Ածխաջրեր — 51 գ Սպիտակուցներ — 4,3 գ Էներգետիկ արժեքը — 536 կկալ/2244 կՋ",
										200
								),
								13, Product.of(
										13, "Պանրի", "media/chips-panir.png",
										"Բաղադրությունը` կարտոֆիլի չոր պյուրե, ցորենի ալյուր, կարտոֆիլի օսլա, արևածաղկի ձեթ, խմելու ջուր, կերակրի աղ, համալիր համային հավելում «Պանիր»: Պիտանիության ժամկետը՝ 150 օր արտադրման օրից պահման պայմանների դեպքում: Պահել օդի 25˚C -ից ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր  — 35 գ Ածխաջրեր — 51 գ Սպիտակուցներ — 4,3 գ Էներգետիկ արժեքը — 536 կկալ/2244 կՋ",
										240
								)
						)
				),
				ProductCategory.of(
						3, "Պահածոներ", "Որակյալ պահածոյացված մթերքներ",
						"media/antari.png", null,
						Map.of(
								14, Product.of(
										14, "Սմբուկի խավիար", "media/xaviar.png",
										"Բաղադրությունը` սմբուկ, տոմատի մածուկ, քաղցր կարմիր պղպեղ, արևածաղկի ձեթ, գլուխ սոխ, թարմ գազար, կերակրի աղ, շաքարավազ, մաղադանոս,աղացած կարմիր կծու պղպեղ ։ Պիտանիության ժամկետը` 24 ամիս արտադրման օրից պահման պայմանները պահպանելու դեպքում: Պահել 0˚-ից մինչև +25˚C ջերմաստիճանի և 75%–ից ոչ ավել օդի հարաբերական խոնավության պայմաններում: Բացելուց հետո  պահել սառնարանում +2˚C մինչև +6˚C ջերմաստիճանի պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր — 7գ Ածխաջրեր — 12գ Սպիտակուցներ — 1գ Էներգետիկ արժեքը — 115կկալ/480կՋ",
										550
								),
								15, Product.of(
										15, "Աջիկա", "media/ajika.png",
										"Բաղադրությունը` քաղցր կարմիր պղպեղ, տոմատի մածուկ, արևածաղկի ձեթ, կերակրի աղ, շաքարավազ, սխտոր, համեմունքներ՝ համեմի սերմ աղացած, կարմիր կծու պղպեղ աղացած: Պիտանիության ժամկետը` 24 ամիս արտադրման օրից պահման պայմանները պահպանելու դեպքում: Պահել չոր և զով տեղում 0˚-ից մինչև +25˚C  ջերմաստիճանի և 75%-ից ոչ ավել օդի հարաբերական խոնավության պայմաններում: Բացելուց հետո  պահել սառնարանում +2˚C մինչև +6˚C ջերմաստիճանի պայմաններում: Սննդային արժեքը 100 գրամում`Ճարպեր — 2գ Ածխաջրեր — 15գ Էներգետիկ արժեքը — 78կկալ/327կՋ",
										550
								),
								16, Product.of(
										16, "Տոմատի մածուկ", "media/tomat.png",
										"Բաղադրությունը` լոլիկ: Պիտանիության ժամկետը` 24 ամիս արտադրման օրից: Պահել չոր և զով տեղում 0˚-ից մինչև +25˚C ջերմաստիճանի և 75%-ից ոչ ավել օդի  հարաբերական խոնավության պայմաններում:   Բացելուց հետո պահել սառնարանում: Սննդային արժեքը 100 գրամում` Ածխաջրեր — 15 Սպիտակուցներ — 4.8գ Էներգետիկ արժեքը — 82կկալ/343կՋ",
										600
								),
								17, Product.of(
										17, "Ախորժակ", "media/axorjak.png",
										"Բաղադրությունը` լոլիկ, կծու ծիծակ, արևածաղկի ձեթ, կերակրի աղ, շաքարավազ, սխտոր, մաղադանոս, համեմունքներ՝ սև պղպեղ աղացած, համեմի սերմ աղացած, կարմիր կծու պղպեղ աղացած։ Պիտանիության ժամկետը` 24 ամիս արտադրման օրից պահման պայմանները պահպանելու դեպքում: Պահել 0˚-ից մինչև +25˚C ջերմաստիճանի և 75%–ից ոչ ավել օդի հարաբերական խոնավության պայմաններում: Բացելուց հետո պահել սառնարանում +2˚C մինչև +6˚C ջերմաստիճանի պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր — 8,0գ Ածխաջրեր — 9,0գ Սպիտակուցներ — 1.5գ Էներգետիկ արժեքը — 114կկալ/477կՋ",
										630
								)
						)
				),
				ProductCategory.of(
						4, "Պաղպաղակ", "Համեղ և զովացուցիչ պաղպաղակ",
						"media/acecream.png", "media/sendvich.mp4",
						Map.of(
								18, Product.of(
										18, "Շոկոլադային կոն", "media/kon-shokolad.png",
										"Բաղադրությունը՝ խմելու ջուր, շաքար, չոր կաթ, սերուցքային կարագ, կակաոյի փոշի, կայունացուցիչներ, վանիլին, գետնանուշ, վաֆլե կոն, շոկոլադե ջնարակ, վանիլին։\n" +
												"                                        Պահել՝ -18˚C-ից ոչ բարձր ջերմաստիճանի պայմաններում: Պիտանիության ժամկետը 12 ամսից ոչ ավել արտադրման օրից՝ պահման պայմանները պահպանելու դեպքում։\n" +
												"                                        Սննդային արժեքը 100 գրամում`\n" +
												"                                        Սպիտակուցներ-6գ\n" +
												"                                        ճարպեր- 11,5գ\n" +
												"                                        Ածխաջրեր- 36գ\n" +
												"                                        Էներգետիկ արժեքը-271կկալ /1135կՋ\n" +
												"                                        Յուղի զանգվածային բաժինը կաթնային մասում՝ 5,4%",
										320
								),
								19, Product.of(
										19, "Վանիլային կոն", "media/kon-vanil.png",
										"Բաղադրությունը՝ խմելու ջուր, շաքար, չոր կաթ, սերուցքային կարագ,  կայունացուցիչներ, վանիլին, գետնանուշ, վաֆլե կոն, շոկոլադե ջնարակ, վանիլին։ Պահել՝ -18˚C ջերմաստիճանի պայմաններում: Պիտանիության ժամկետը 12 ամսից ոչ ավել արտադրման օրից՝ պահման պայմանները պահպանելու դեպքում։\n" +
												"                                            Սննդային արժեքը 100 գրամում`\n" +
												"                                            Սպիտակուցներ-5,5գ\n" +
												"                                            ճարպեր- 11,2գ\n" +
												"                                            Ածխաջրեր-36,7գ\n" +
												"                                            Էներգետիկ արժեքը-270կկալ /1130կՋ\n" +
												"                                            Յուղի զանգվածային բաժինը կաթնային մասում՝ 5,2%",
										320
								),
								20, Product.of(
										20, "Շոկոլադային բրիկետ", "media/briket-shoko.png",
										"Բաղադրությունը` խմելու ջուր, շաքար, չոր անարատ կաթ, չոր յուղազերծ կաթ, սերուցքային կարագ, կակաոյի փոշի, կայունացուցիչներ, էմուլգատոր: Պահել՝ -18° C-ից ոչ բարձր ջերմաստիճանի պայմաններում: Պիտանիության ժամկետը 12 ամսից ոչ ավել արտադրման օրից՝ պահման պայմանները պահպանելու դեպքում:\n" +
												"                                                Սննդային արժեքը 100 գրամում`\n" +
												"                                                Սպիտակուցներ-4,8գ\n" +
												"                                                ճարպեր- 5,4գ\n" +
												"                                                Ածխաջրեր-25գ\n" +
												"                                                Էներգետիկ արժեքը-168կկալ /703կՋ\n" +
												"                                                Յուղի զանգվածային բաժինը կաթնային մասում՝ 5,4%",
										350
								)
						)
				),
				ProductCategory.of(
						5, "Գարեջուր", "Համեղ և զովացուցիչ պաղպաղակ",
						"media/garejur.png", null,
						emptyProductMap()
				),
				ProductCategory.of(
						6, "Մակարոն", "Թարմ և որակյալ պաղպաղակ",
						"media/makaron.jpg", null,
						emptyProductMap()
				),
				ProductCategory.of(
						7, "Պոպկորն", "Թարմ և որակյալ պոպկորն",
						"media/popcorn.png", null,
						emptyProductMap()
				),
				ProductCategory.of(
						8, "Քաղցր ձողիկներ", "Խրթխրթան և համեղ ձողիկներ",
						"media/dzoxik.png", null,
						emptyProductMap()
				)
		);

		// Initialize the map with the initial categories
		for (ProductCategory category : initialCategories) {
			categories.put(category.id(), category);
		}
	}

	public List<ProductCategory> list() {
		return new ArrayList<>(categories.values());
	}

	public Optional<ProductCategory> getCategoryById(int id) {
		return Optional.ofNullable(categories.get(id));
	}

	public ProductCategory addCategory(ProductCategory category) {
		int id = categoryIdGenerator.getAndIncrement();
		ProductCategory categoryWithId = ProductCategory.of(
				id,
				category.title(),
				category.description(),
				category.coverImage(),
				category.coverVideo(),
				emptyProductMap()
		);
		categories.put(id, categoryWithId);
		return categoryWithId;
	}

	public void removeCategory(int id) {
		categories.remove(id);
	}

	public boolean updateCategory(int id, ProductCategory category) {
		if (categories.containsKey(id)) {
			ProductCategory existingCategory = categories.get(id);
			ProductCategory newCategory = ProductCategory.of(
					id,
					category.title(),
					category.description(),
					category.coverImage(),
					category.coverVideo(),
					existingCategory.products()
			);
			categories.put(id, newCategory);
			return true;
		}
		return false;
	}

	// Helper method to create an empty product map
	public Stream<Product> getProductById(int id) {
		return categories.values().stream()
				.filter(category -> category.products().containsKey(id))
				.map(category -> category.products().get(id));
	}

	public boolean addProductToCategory(int categoryId, Product product) {
		if (categories.containsKey(categoryId)) {
			ProductCategory category = categories.get(categoryId);
			Product productWithId = Product.of(
					productIdGenerator.getAndIncrement(),
					product.title(),
					product.image(),
					product.description(),
					product.price()
			);
			Map<Integer, Product> updatedProducts = new LinkedHashMap<>(category.products());
			updatedProducts.put(productWithId.id(), productWithId);

			ProductCategory updatedCategory = ProductCategory.of(
					category.id(),
					category.title(),
					category.description(),
					category.coverImage(),
					category.coverVideo(),
					updatedProducts
			);

			categories.put(categoryId, updatedCategory);
			return true;
		}
		return false;
	}

	public boolean updateProduct(int categoryId, int productId, Product product) {
		if (categories.containsKey(categoryId)) {
			ProductCategory category = categories.get(categoryId);
			Map<Integer, Product> products = category.products();

			if (products.containsKey(productId)) {
				Map<Integer, Product> updatedProducts = new LinkedHashMap<>(products);
				updatedProducts.put(productId, Product.of(
						productId,
						product.title(),
						product.image(),
						product.description(),
						product.price()
				));

				ProductCategory updatedCategory = ProductCategory.of(
						category.id(),
						category.title(),
						category.description(),
						category.coverImage(),
						category.coverVideo(),
						updatedProducts
				);

				categories.put(categoryId, updatedCategory);
				return true;
			}
		}
		return false;
	}

	public boolean removeProductFromCategory(int categoryId, int productId) {
		if (categories.containsKey(categoryId)) {
			ProductCategory category = categories.get(categoryId);
			Map<Integer, Product> products = category.products();

			if (products.containsKey(productId)) {
				Map<Integer, Product> updatedProducts = new LinkedHashMap<>(products);
				updatedProducts.remove(productId);

				ProductCategory updatedCategory = ProductCategory.of(
						category.id(),
						category.title(),
						category.description(),
						category.coverImage(),
						category.coverVideo(),
						updatedProducts
				);

				categories.put(categoryId, updatedCategory);
				return true;
			}
		}
		return false;
	}

	private Map<Integer, Product> emptyProductMap() {
		return new LinkedHashMap<>();
	}
}
