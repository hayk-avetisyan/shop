package am.techmock.shop.repository;

import am.techmock.shop.model.Product;
import am.techmock.shop.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {
    private final List<ProductCategory> categories = new ArrayList<>();
    private final AtomicInteger categoryIdGenerator = new AtomicInteger(9); // Start from 9 to avoid conflicts with existing IDs
    private final AtomicInteger productIdGenerator = new AtomicInteger(21); // Start from 21 to avoid conflicts with existing IDs

    public ProductRepository() {
        categories.addAll(List.of(
                ProductCategory.of(
                        1, "Արևածաղկի սերմեր", "Թարմ և որակյալ արևածաղկի սերմեր",
                        "media/otborniy.png", null,
                        List.of(
                                Product.of(
                                        1, "Սովորական", "media/otborniy.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը՝ 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում, օդի  25˚C ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
                                        200
                                ),
                                Product.of(
                                        2, "Բոված ծովի աղով", "media/arevacaxik-axi.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը՝ 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում, օդի  25˚C ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
                                        200
                                ),
                                Product.of(
                                        3, "Գծավոր բոված սերմեր ծովի աղով", "media/gcavor axi.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր, աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում  օդի 25˚C ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 49.6 գ Ածխաջրեր – 19.5 գ Սպիտակուցներ – 22.8 գ Էներգետիկ արժեքը – 616 կկալ/2579կՋ",
                                        350
                                ),
                                Product.of(
                                        4, "Գծավոր բոված սերմեր", "media/gcavor.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում  օդի 25˚C ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
                                        350
                                ),
                                Product.of(
                                        5, "Աղով և առանց աղի միքս", "media/mix.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր, ծովի աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում օդի 25˚C  ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/2420կՋ",
                                        350
                                ),
                                Product.of(
                                        6, "Սպիտակ բոված սերմեր ծովի աղով", "media/spitak-axi.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր, կերակրի աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում օդի 25 oC ոչ բարձր ջերմաստիճանի և 75 %-ից ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 34.5 գ Ածխաջրեր – 5.44 գ Սպիտակուցներ – 17.06 գ Էներգետիկ արժեքը – 386 կկալ/1659ԿՋ",
                                        350
                                ),
                                Product.of(
                                        7, "«Մոլոդյոժնիե» հատընտիր բոված սերմեր", "media/molod.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով վայրում օդի 25˚C ջերմաստիճանից ոչ բարձր և 75 % ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 579 կկալ/ 2421,82 կՋ",
                                        400
                                ),
                                Product.of(
                                        8, "«Մոլոդյոժնիե» հատընտիր բոված սերմեր ծովի աղով", "media/molod-axi.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր, ծովի աղ: Պիտանիության ժամկետը` 180 օր արտադրման օրից պահման պայմանների դեպքում: Պահել չոր և զով  վայրում օդի 25˚C ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել  հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52,9 գ Ածխաջրեր – 5,0 գ գ Սպիտակուցներ – 20,7 գ Էներգետիկ արժեքը – 579կկալ/ 2424կՋ",
                                        400
                                ),
                                Product.of(
                                        9, "«Օսոբեննիյե» հատընտիր բոված սերմեր ծովի աղով", "media/osob.jpg",
                                        "Բաղադրությունը` արևածաղկի  սերմեր: Պիտանիության ժամկետը` ոչ ավել, քան 350 օր արտադրման և փաթեթավորման օրից` պահման պայմանների դեպքում, օդի 25°C ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 578 կկալ/2394 կՋ",
                                        420
                                ),
                                Product.of(
                                        10, "«Օսոբեննիյե» հատընտիր բոված սերմեր ծովի աղով", "media/osob-axi.png",
                                        "Բաղադրությունը` արևածաղկի  սերմեր, ծովի աղ: Պիտանիության ժամկետը` ոչ ավել, քան 350 օր արտադրման և փաթեթավորման օրից` պահման պայմանների դեպքում, օդի 25°C ոչ բարձր ջերմաստիճանի և 75 % ոչ ավել հարաբերական խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր – 52.9 գ Ածխաջրեր – 5.0 գ Սպիտակուցներ – 20.7 գ Էներգետիկ արժեքը – 578 կկալ/2394 կՋ",
                                        420
                                )
                        )
                ),
                ProductCategory.of(
                        2, "Չիպսեր", "Խրթխրթան և համեղ չիպսեր",
                        "media/chips.jpg", "media/chips.mp4",
                        List.of(
                                Product.of(
                                        11, "Դասական", "media/chips-dasakan.png",
                                        "Բաղադրությունը` կարտոֆիլի չոր պյուրե, ցորենի ալյուր, կարտոֆիլի օսլա, արևածաղկի ձեթ, խմելու ջուր, կերակրի աղ: Պիտանիության ժամկետը՝ 150 օր արտադրման օրից պահման պայմանների դեպքում: Պահել օդի 25˚C -ից ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր  — 35 գ Ածխաջրեր — 51 գ Սպիտակուցներ — 4,3 գ Էներգետիկ արժեքը — 536 կկալ/2244 կՋ",
                                        200
                                ),
                                Product.of(
                                        12, "Թթվասերի", "media/chips-ttvaser.png",
                                        "Բաղադրությունը` կարտոֆիլի չոր պյուրե, ցորենի ալյուր, կարտոֆիլի օսլա, արևածաղկի ձեթ, խմելու ջուր, կերակրի աղ, համալիր համային հավելում «Թթվասեր»: Պիտանիության ժամկետը՝ 150 օր արտադրման օրից պահման պայմանների դեպքում: Պահել օդի 25˚C -ից ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր — 35 գ Ածխաջրեր — 51 գ Սպիտակուցներ — 4,3 գ Էներգետիկ արժեքը — 536 կկալ/2244 կՋ",
                                        200
                                ),
                                Product.of(
                                        13, "Պանրի", "media/chips-panir.png",
                                        "Բաղադրությունը` կարտոֆիլի չոր պյուրե, ցորենի ալյուր, կարտոֆիլի օսլա, արևածաղկի ձեթ, խմելու ջուր, կերակրի աղ, համալիր համային հավելում «Պանիր»: Պիտանիության ժամկետը՝ 150 օր արտադրման օրից պահման պայմանների դեպքում: Պահել օդի 25˚C -ից ոչ բարձր ջերմաստիճանի և 75%-ից ոչ ավել խոնավության պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր  — 35 գ Ածխաջրեր — 51 գ Սպիտակուցներ — 4,3 գ Էներգետիկ արժեքը — 536 կկալ/2244 կՋ",
                                        240
                                )
                        )
                ),
                ProductCategory.of(
                        3, "Պահածոներ", "Որակյալ պահածոյացված մթերքներ",
                        "media/antari.png", null,
                        List.of(
                                Product.of(
                                        14, "Սմբուկի խավիար", "media/xaviar.png",
                                        "Բաղադրությունը` սմբուկ, տոմատի մածուկ, քաղցր կարմիր պղպեղ, արևածաղկի ձեթ, գլուխ սոխ, թարմ գազար, կերակրի աղ, շաքարավազ, մաղադանոս,աղացած կարմիր կծու պղպեղ ։ Պիտանիության ժամկետը` 24 ամիս արտադրման օրից պահման պայմանները պահպանելու դեպքում: Պահել 0˚-ից մինչև +25˚C ջերմաստիճանի և 75%–ից ոչ ավել օդի հարաբերական խոնավության պայմաններում: Բացելուց հետո  պահել սառնարանում +2˚C մինչև +6˚C ջերմաստիճանի պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր — 7գ Ածխաջրեր — 12գ Սպիտակուցներ — 1գ Էներգետիկ արժեքը — 115կկալ/480կՋ",
                                        550
                                ),
                                Product.of(
                                        15, "Աջիկա", "media/ajika.png",
                                        "Բաղադրությունը` քաղցր կարմիր պղպեղ, տոմատի մածուկ, արևածաղկի ձեթ, կերակրի աղ, շաքարավազ, սխտոր, համեմունքներ՝ համեմի սերմ աղացած, կարմիր կծու պղպեղ աղացած: Պիտանիության ժամկետը` 24 ամիս արտադրման օրից պահման պայմանները պահպանելու դեպքում: Պահել չոր և զով տեղում 0˚-ից մինչև +25˚C  ջերմաստիճանի և 75%-ից ոչ ավել օդի հարաբերական խոնավության պայմաններում: Բացելուց հետո  պահել սառնարանում +2˚C մինչև +6˚C ջերմաստիճանի պայմաններում: Սննդային արժեքը 100 գրամում`Ճարպեր — 2գ Ածխաջրեր — 15գ Էներգետիկ արժեքը — 78կկալ/327կՋ",
                                        550
                                ),
                                Product.of(
                                        16, "Տոմատի մածուկ", "media/tomat.png",
                                        "Բաղադրությունը` լոլիկ: Պիտանիության ժամկետը` 24 ամիս արտադրման օրից: Պահել չոր և զով տեղում 0˚-ից մինչև +25˚C ջերմաստիճանի և 75%-ից ոչ ավել օդի  հարաբերական խոնավության պայմաններում:   Բացելուց հետո պահել սառնարանում: Սննդային արժեքը 100 գրամում` Ածխաջրեր — 15 Սպիտակուցներ — 4.8գ Էներգետիկ արժեքը — 82կկալ/343կՋ",
                                        600
                                ),
                                Product.of(
                                        17, "Ախորժակ", "media/axorjak.png",
                                        "Բաղադրությունը` լոլիկ, կծու ծիծակ, արևածաղկի ձեթ, կերակրի աղ, շաքարավազ, սխտոր, մաղադանոս, համեմունքներ՝ սև պղպեղ աղացած, համեմի սերմ աղացած, կարմիր կծու պղպեղ աղացած։ Պիտանիության ժամկետը` 24 ամիս արտադրման օրից պահման պայմանները պահպանելու դեպքում: Պահել 0˚-ից մինչև +25˚C ջերմաստիճանի և 75%–ից ոչ ավել օդի հարաբերական խոնավության պայմաններում: Բացելուց հետո պահել սառնարանում +2˚C մինչև +6˚C ջերմաստիճանի պայմաններում: Սննդային արժեքը 100 գրամում` Ճարպեր — 8,0գ Ածխաջրեր — 9,0գ Սպիտակուցներ — 1.5գ Էներգետիկ արժեքը — 114կկալ/477կՋ",
                                        630
                                )
                        )
                ),
                ProductCategory.of(
                        4, "Պաղպաղակ", "Համեղ և զովացուցիչ պաղպաղակ",
                        "media/acecream.png", "media/sendvich.mp4",
                        List.of(
                                Product.of(
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
                                Product.of(
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
                                Product.of(
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
                        List.of()
                ),
                ProductCategory.of(
                        6, "Մակարոն", "Թարմ և որակյալ պաղպաղակ",
                        "media/makaron.jpg", null,
                        List.of()
                ),
                ProductCategory.of(
                        7, "Պոպկորն", "Թարմ և որակյալ պոպկորն",
                        "media/popcorn.png", null,
                        List.of()
                ),
                ProductCategory.of(
                        8, "Քաղցր ձողիկներ", "Խրթխրթան և համեղ ձողիկներ",
                        "media/dzoxik.png", null,
                        List.of()
                )
        ));
    }

    public List<ProductCategory> list() {
        return new ArrayList<>(categories);
    }

    public Optional<ProductCategory> getCategoryById(int id) {
        return categories.stream()
                .filter(category -> category.id() == id)
                .findFirst();
    }

    public ProductCategory addCategory(ProductCategory category) {
        ProductCategory categoryWithId = ProductCategory.of(
                categoryIdGenerator.getAndIncrement(),
                category.title(),
                category.description(),
                category.coverImage(),
                category.coverVideo(),
                new ArrayList<>()
        );
        categories.add(categoryWithId);
        return categoryWithId;
    }

    public void removeCategory(int id) {
        categories.removeIf(category -> category.id() == id);
    }

    public boolean updateCategory(int id, ProductCategory updatedCategory) {
        Optional<ProductCategory> existingCategoryOpt = getCategoryById(id);

        if (existingCategoryOpt.isPresent()) {
            ProductCategory existingCategory = existingCategoryOpt.get();
            ProductCategory newCategory = ProductCategory.of(
                    id,
                    updatedCategory.title(),
                    updatedCategory.description(),
                    updatedCategory.coverImage(),
                    updatedCategory.coverVideo(),
                    existingCategory.products()
            );

            removeCategory(id);
            categories.add(newCategory);
            return true;
        }

        return false;
    }

    public Optional<Product> getProductById(int id) {
        return categories.stream()
                .flatMap(category -> category.products().stream())
                .filter(product -> product.id() == id)
                .findFirst();
    }

    public boolean addProductToCategory(int categoryId, Product product) {
        Optional<ProductCategory> categoryOpt = getCategoryById(categoryId);

        if (categoryOpt.isPresent()) {
            ProductCategory category = categoryOpt.get();

            Product productWithId = Product.of(
                    productIdGenerator.getAndIncrement(),
                    product.title(),
                    product.image(),
                    product.description(),
                    product.price()
            );

            category.products().add(productWithId);
            return true;
        }

        return false;
    }

    public boolean removeProductFromCategory(int categoryId, int productId) {
        Optional<ProductCategory> categoryOpt = getCategoryById(categoryId);

        if (categoryOpt.isPresent()) {
            ProductCategory category = categoryOpt.get();
            return category.products().removeIf(product -> product.id() == productId);
        }

        return false;
    }

    public boolean updateProduct(int categoryId, int productId, Product updatedProduct) {
        Optional<ProductCategory> categoryOpt = getCategoryById(categoryId);

        if (categoryOpt.isPresent()) {
            ProductCategory category = categoryOpt.get();

            Optional<Product> existingProductOpt = category.products().stream()
                    .filter(p -> p.id() == productId)
                    .findFirst();

            if (existingProductOpt.isPresent()) {
                category.products().removeIf(p -> p.id() == productId);

                Product newProduct = Product.of(
                        productId,
                        updatedProduct.title(),
                        updatedProduct.image(),
                        updatedProduct.description(),
                        updatedProduct.price()
                );

                category.products().add(newProduct);

                return true;
            }
        }

        return false;
    }
}
