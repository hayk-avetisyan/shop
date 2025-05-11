
document.addEventListener("DOMContentLoaded", function () {
    const flavorOptions = {
        "Արևածաղիկ": ["Սովորական", "Աղի"],
        "Չիպս": ["Դասական", "Թթվասերի", "Պանրի", "Խորովածի", "Բեկոնի", "Լոլիկի"],
        "Պաղպաղակ": ["Կոն Շոկոլադային", "Կոն Վանիլային"],
        "Գարեջուր": ["Բաց", "Մուգ"],
        "Քաղցր ձողիկներ": ["Վանիլի", "Խտացրած կաթի", "Բանանի"],
        "Պահածո": ["Սմբուկի խավիար", "Աջիկա", "Ծիրանի ջեմ"],
        "Պոպկորն": ["Աղով", "Կարամելապատ"],
        "Մակարոն": ["Պերյա", "Սպիռալ", "Վերմիշել"],
    };

    const productPrices = {
        "Արևածաղիկ": 400,
        "Չիպս": 450,
        "Պաղպաղակ": 250,
        "Գարեջուր": 960,
        "Քաղցր ձողիկներ": 200,
        "Պահածո": 780,
        "Պոպկորն": 300,
        "Մակարոն": 430,
    };

    const popup = document.getElementById("popup");
    const quantityInput = document.getElementById("quantity");
    const flavorSelect = document.getElementById("flavor");
    const flavorContainer = document.getElementById("flavor-container");
    const addToCartBtn = document.getElementById("add-to-cart-btn");

    let currentProduct = "";
    let currentPrice = 0;

    // Զամբյուղում ապրանք ավելացնելու կոդը
    // addToCartBtn.addEventListener("click", function () {
    //     const quantity = parseInt(quantityInput.value); // Քանակի ստացում
    //     const selectedFlavor = flavorSelect.value || ""; // Համի ընտրություն
    //
    //     let productText = currentProduct;
    //     if (selectedFlavor) {
    //         productText += ` (${selectedFlavor})`; // Համը ավելացվում է ապրանքի անվանը
    //     }
    //
    //     // Զամբյուղում ավելացմանը հաշվարկեք գինը
    //     const totalPrice = currentPrice * quantity;
    //
    //     const cartList = document.getElementById("cart-items");
    //     const listItem = document.createElement("li");
    //     listItem.textContent = `${productText} - ${quantity} հատ - ${totalPrice} դրամ`; // Ապրանքը և գինը ավելացվում է զամբյուղ
    //
    //     // Ապրանքի ջնջման կոճակը
    //     const removeBtn = document.createElement("button");
    //     removeBtn.textContent = "Ջնջել";
    //     removeBtn.classList.add("remove-btn");
    //     removeBtn.addEventListener("click", function () {
    //         cartList.removeChild(listItem);
    //         updateTotalPrice(); // Թարմացնել ընդհանուր գինը
    //     });
    //
    //     listItem.appendChild(removeBtn);
    //     cartList.appendChild(listItem);
    //
    //     // Արժեքի թարմացում զամբյուղում
    //     updateTotalPrice();
    //
    //     // Պատուհանը փակելու և դաշտերը մաքրելու կոդը
    //     popup.style.display = "none";
    //     quantityInput.value = 1;
    // });

    // Զամբյուղի ընդհանուր արժեքի թարմացում
    // function updateTotalPrice() {
    //     const cartItems = document.querySelectorAll("#cart-items li");
    //     let total = 0;
    //
    //     cartItems.forEach((item) => {
    //         const priceText = item.textContent.split(' - ')[2]; // Գինը տեքստից բաժանում ենք
    //         const price = parseInt(priceText.replace(' դրամ', '')); // Հանում ենք 'դրամ' և ստանում գինը
    //         total += price;
    //     });
    //
    //     document.getElementById("total-price").textContent = `${total} դրամ`; // Արժեքի թարմացում
    // }

    // Popup պատուհանի փակումը
    // document.getElementById("close-btn").addEventListener("click", function () {
    //     popup.style.display = "none";
    // });
});

document.addEventListener("DOMContentLoaded", function () {
    // Ապրանքների խմբերի վրա կլիկ անելուց բացվում է համապատասխան փոփափը
   document.getElementById('product-semchka').addEventListener('click', function() {
    document.getElementById('product-semchka__popup').style.display = 'block';
});


document.getElementById('product-icecream').addEventListener('click', function() {
    document.getElementById('product-icecream__popup').style.display = 'block';
});


document.getElementById('product-chips').addEventListener('click', function() {
    document.getElementById('product-chips__popup').style.display = 'block';
});


document.getElementById('product-pahaco').addEventListener('click', function() {
    document.getElementById('product-pahaco__popup').style.display = 'block';
});



// Փոփափը փակելու գործառույթը «Արևածաղիկ» փոփափի փակման կոճակի միջոցով
document.getElementById('close-btn-semchka').addEventListener('click', function() {
    document.getElementById('product-semchka__popup').style.display = 'none';
});

// Փոփափը փակելու գործառույթը «Պաղպաղակ» փոփափի փակման կոճակի միջոցով
document.getElementById('close-btn-icecream').addEventListener('click', function() {
    document.getElementById('product-icecream__popup').style.display = 'none';
});

document.getElementById('close-btn-chips').addEventListener('click', function() {
    document.getElementById('product-chips__popup').style.display = 'none';
});

document.getElementById('close-btn-pahaco').addEventListener('click', function() {
    document.getElementById('product-pahaco__popup').style.display = 'none';
});




// Փոփափը փակելու գործառույթը՝ փոփափի պարունակության դուրս կլիկ անելու միջոցով «Արևածաղիկ»
document.getElementById('product-semchka__popup').addEventListener('click', function(event) {
    if (event.target === document.getElementById('product-semchka__popup')) {
        document.getElementById('product-semchka__popup').style.display = 'none';
    }
});

// Փոփափը փակելու գործառույթը՝ փոփափի պարունակության դուրս կլիկ անելու միջոցով «Պաղպաղակ»
document.getElementById('product-icecream__popup').addEventListener('click', function(event) {
    if (event.target === document.getElementById('product-icecream__popup')) {
        document.getElementById('product-icecream__popup').style.display = 'none';
    }
});
document.getElementById('product-chips__popup').addEventListener('click', function(event) {
    if (event.target === document.getElementById('product-chips__popup')) {
        document.getElementById('product-chips__popup').style.display = 'none';
    }
});
document.getElementById('product-pahaco__popup').addEventListener('click', function(event) {
    if (event.target === document.getElementById('product-pahaco__popup')) {
        document.getElementById('product-pahaco__popup').style.display = 'none';
    }
});

    // Ապրանքների կատեգորիաների տեսքը պատրաստելու ֆունկցիա
    const categories = document.querySelectorAll(".category");
    function revealCategories() {
        categories.forEach(category => {
            const rect = category.getBoundingClientRect();
            if (rect.top < window.innerHeight - 100) {
                category.classList.add("show");
            }
        });
    }

    window.addEventListener("scroll", revealCategories);
    revealCategories();
});

// Ստանում ենք մոդալը
let modal = document.getElementById("paymentModal");

// Պատվիրել կոճակի վրա սեղմելու համար
document.getElementById('orderBtn').addEventListener('click', function() {
  const cartItems = document.getElementById('cart-items').children;
  const totalPrice = document.getElementById('total-price').innerText;

  if (cartItems.length === 0) {
    alert('Ձեր զամբյուղը դատարկ է!');
  } else {
    modal.style.display = "block"; // Բացում ենք մոդալը
  }
});

// Մոդալը փակելու կոճակը
document.getElementById('closeModal').addEventListener('click', function() {
  modal.style.display = "none"; // Փակում ենք մոդալը
});

// Մոդալը փակելու համար նաև պատուհանի վրա սեղմելով
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none"; // Փակում ենք մոդալը
  }
};

// Վճարումը հաստատելու կոճակը
document.getElementById('submitPayment').addEventListener('click', function () {
  const cardNumberInput = document.getElementById('cardNumber');
  const cardNameInput = document.getElementById('cardName');
  const expiryDateInput = document.getElementById('expiryDate');
  const cvvInput = document.getElementById('cvv');

  const cardNumber = cardNumberInput.value.trim();
  const cardName = cardNameInput.value.trim();
  const expiryDate = expiryDateInput.value.trim();
  const cvv = cvvInput.value.trim();

  const cardNumberRegex = /^\d{16}$/;
  const expiryDateRegex = /^(0[1-9]|1[0-2])\/\d{2}$/;
  const cvvRegex = /^\d{3}$/;
  const nameRegex = /^[Ա-ՖԱ-ֆA-Za-z\s]{3,}$/;

  let valid = true;

  // Clear previous errors
  document.querySelectorAll('.error-message').forEach(el => el.textContent = '');

  if (!cardNumberRegex.test(cardNumber.replace(/\s+/g, ''))) {
    showError('cardNumberError', 'Քարտի համարը պետք է լինի 16 թվանշան:');
    valid = false;
  }

  if (!nameRegex.test(cardName)) {
    showError('cardNameError', 'Մուտքագրեք ճիշտ անուն և ազգանուն:');
    valid = false;
  }

  if (!expiryDateRegex.test(expiryDate)) {
    showError('expiryDateError', 'Մուտքագրեք ժամկետ՝ MM/YY ձևաչափով:');
    valid = false;
  }

  if (!cvvRegex.test(cvv)) {
    showError('cvvError', 'CVV-ը պետք է լինի正3 թվանշան:');
    valid = false;
  }

  if (!valid) return;

  // Եթե ամեն բան ճիշտ է
  alert('Վճարումը հաջողությամբ կատարվեց');
  modal.style.display = "none";

  // Մաքրում ենք զամբյուղը
  document.getElementById('cart-items').innerHTML = '';
  document.getElementById('total-price').innerText = '0';

  // Մաքրում ենք մուտքագրված տվյալները
  cardNumberInput.value = '';
  cardNameInput.value = '';
  expiryDateInput.value = '';
  cvvInput.value = '';
});

// Ֆունկցիա՝ սխալի հաղորդագրություն ցույց տալու համար
function showError(elementId, message) {
  const errorElement = document.getElementById(elementId);
  if (errorElement) {
    errorElement.textContent = message;
  }
}
