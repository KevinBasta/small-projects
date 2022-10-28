// Given the ingredients a player has, what types of tea can they make 
// that would maximize the profit? 

//this constant gets the li block that has the ingredent selector and amount
const item = document.getElementById("Ingredient0");

/*this adds an event listener to the add Ingredient button, it adds the same section again
 *and it changes the idNumbers so each new ingredent property is accesable with a new id */
const addIngredient = document.getElementById("addIngredient");
addIngredient.addEventListener("click", function(){
    var clone = item.cloneNode(true);
    clone.id = "Ingredient" + Ingredients.children.length;
    clone.children[0].children[0].id = "Ingredient_Select" + Ingredients.children.length;
    clone.children[0].children[1].id = "ingredient_number" + Ingredients.children.length;
    clone.children[0].children[2].addEventListener("click", function(){
                Ingredients.removeChild(this.parentNode.parentNode);
            });
    Ingredients.appendChild(clone);
});



var tea_types_One = [
					{name:"Golden_Monkey_tea", ingredients:["Golden leaf shoots"]},
					{name:"Cloud_tea", ingredients:["Cloud leaf"]},
					{name:"White_Dragon_tea", ingredients:["White Dragon petals"]},
					{name:"Ginseng_tea", ingredients:["Ginseng root"]},
					{name:"Silver_needle_tea", ingredients:["Silver leaf shoots"]},
					{name:"Kelp_tea", ingredients:["Kelp"]},
					{name:"Black_tea", ingredients:["Black tea leaves"]},
					{name:"Kombucha_tea", ingredients:["Kombucha fungus"]},
					{name:"Mint_tea", ingredients:["Mint leaves"]},
					{name:"Cinnamon_tea", ingredients:["Cinnamon stick"]},
					{name:"Barley_tea", ingredients:["Barley"]},
					{name:"Oolong_tea", ingredients:["Oolong leaves"]},
					{name:"Lychee_tea", ingredients:["Lychee nuts"]},
					{name:"Ginger_tea", ingredients:["Ginger root"]},
					{name:"White_jade_tea", ingredients:["White jade petals"]},
					{name:"Green_tea", ingredients:["Green tea leaves"]},
					{name:"Jasmine_tea", ingredients:["Jasmine petals"]}
					];

var tea_types_Two = [
					{name:"Panda_tea", ingredients:["Panda Dung", "Green tea leaves"]},
					{name:"Tienchi_Flower_tea", ingredients:["Tienchi flower", "Ginseng root"]},
					{name:"Bubble_tea", ingredients:["Green tea leaves", "Tapioca powder"]},
					{name:"Swamp_tea", ingredients:["Swamp leaf", "Lychee nuts"]},
					{name:"Papaya_tea ", ingredients:["Green tea leaves", "Papaya"]},
					{name:"Hibiscus_tea", ingredients:["Hibiscus petals", "Cinnamon stick"]},
					{name:"Sakura_tea", ingredients:["Green tea leaves", "Sakura petals"]},
					{name:"Cactus_tea", ingredients:["Cactus", "Aloe Vera"]},
					{name:"Aloe_Vera_tea", ingredients:["Ginger root", "Aloe Vera"]},
					{name:"Flowering_tea", ingredients:["Lily petals", "Jasmine petals"]},
					{name:"Jasmine_Oolong_tea", ingredients:["Oolong leaves", "Jasmine petals"]},
					{name:"Jasmine_Green_tea", ingredients:["Green tea leaves", "Jasmine petals"]},
					{name:"Genmaicha_tea", ingredients:["Green tea leaves", "Brown rice"]},
					{name:"Spiced_tea", ingredients:["Chili pepper", "Cinnamon stick"]},
					{name:"Ginger_green_tea", ingredients:["Ginger root", "Green tea leaves"]}
					];

var ingredent_number = document.getElementById("Ingredients");
var obtained_ingredients = [];
var teas_to_make = [];

function check_for_tea() {
	var obtained_ingredients = [];
	var teas_to_make = [];
	var intNumOfTeaTwo = 0;
	var strIngredientOne = "";
	var strIngredientTwo = "";

	//this saves the ingredients the user has into an array with objects within containing the ingredient and the amount of it
	for (i = 0; i < Ingredients.children.length; i++) {
		current_ingredient = document.getElementById("Ingredient_Select" + [i]).value;
		current_ingredient_amount = document.getElementById("ingredient_number" + [i]).value;
		obtained_ingredients.push({ingredients: (current_ingredient), amount: (current_ingredient_amount)});
	}

	console.log(obtained_ingredients);

	var intPlaceOne = -1;
	var intPlaceTwo = -1;
	var intAmountToMake = 1000;

	//This checks for two item tea crafting
	for(i = 0; i < tea_types_Two.length; i++){
		intPlaceOne = -1;
		intPlaceTwo = -1;
		intAmountToMake = 1000;
		strIngredientOne = tea_types_Two[i].ingredients[0];
		strIngredientTwo = tea_types_Two[i].ingredients[1];
		for(ii = 0; ii < obtained_ingredients.length; ii++){
			if (obtained_ingredients[ii].ingredients == strIngredientOne && obtained_ingredients[ii].amount != 0) {
				intPlaceOne = ii;
				//console.log(intPlaceOne);
				if (obtained_ingredients[ii].amount < intAmountToMake) {
					intAmountToMake = obtained_ingredients[ii].amount;
				}
			}

			else if (obtained_ingredients[ii].ingredients == strIngredientTwo && obtained_ingredients[ii].amount != 0) {
				intPlaceTwo = ii;
				//console.log(intPlaceTwo);
				if (obtained_ingredients[ii].amount < intAmountToMake) {
					intAmountToMake = obtained_ingredients[ii].amount;
				}
			}

			if (intPlaceOne > -1 && intPlaceTwo > -1) {
				teas_to_make.push({name: tea_types_Two[i].name, amount: intAmountToMake});
				obtained_ingredients[intPlaceOne].amount = obtained_ingredients[intPlaceOne].amount - intAmountToMake;
				obtained_ingredients[intPlaceTwo].amount = obtained_ingredients[intPlaceTwo].amount - intAmountToMake;
				break;
			}
		}
	}

	//this checks for one item tea craftings
	for (ii = 0; ii < obtained_ingredients.length; ii++) {
		var ingredients_for_tea_one = obtained_ingredients[ii].ingredients;
		for (iii = 0; iii < tea_types_One.length; iii++) {
			if (tea_types_One[iii].ingredients[0] == ingredients_for_tea_one && obtained_ingredients[ii].amount > 0) {
				teas_to_make.push({name: tea_types_One[iii].name, amount: obtained_ingredients[ii].amount});
				obtained_ingredients[ii].amount -= 1;
			}
		}
	}

	console.log(teas_to_make);

  for(i = 0; i < teas_to_make.length; i++){
    document.getElementById(teas_to_make[i].name).style.backgroundColor = "red";
  }
}
