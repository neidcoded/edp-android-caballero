package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DishViewModel : ViewModel() {
    // The ONE source of truth for the whole app.
    // _dishes is private and writable; dishes is public and read-only.
    private val _dishes = MutableStateFlow(
        listOf(
            Dish(
                id = 1,
                name = "Chicken Adobo",
                recipes = listOf(
                    Recipe(1, "Sauté garlic and onions in a pot."),
                    Recipe(2, "Add chicken pieces and sear until lightly browned."),
                    Recipe(3, "Pour in soy sauce, vinegar, and water."),
                    Recipe(4, "Add peppercorns and bay leaves."),
                    Recipe(5, "Simmer until chicken is tender and sauce is reduced.")
                )
            ),
            Dish(
                id = 2,
                name = "Sinigang na Baboy",
                recipes = listOf(
                    Recipe(6, "Boil water in a pot and add pork cubes."),
                    Recipe(7, "Add onions, tomatoes, and taro. Simmer until pork is tender."),
                    Recipe(8, "Add vegetables like radish, eggplant, and long green beans."),
                    Recipe(9, "Pour in tamarind soup base mix and stir."),
                    Recipe(10, "Add water spinach (kangkong) and turn off heat.")
                )
            )
        )
    )
    val dishes: StateFlow<List<Dish>> = _dishes.asStateFlow()

    // Used to hand out a fresh, unique id every time we create something.
    private var nextId = 100

    // ---------------- DISH CRUD ----------------

    // CREATE -- GIVEN. Study this pattern.
    fun addDish(name: String) {
        if (name.isBlank()) return // ignore empty input
        val newDish = Dish(id = nextId++, name = name.trim())
        _dishes.value = _dishes.value + newDish // NEW list, not a mutation
    }

    // READ -- GIVEN.
    fun getDish(dishId: Int): Dish? {
        return _dishes.value.find { it.id == dishId }
    }

    // UPDATE -- TODO 1 (5 pts)
    // Rename the dish whose id == dishId. Ignore a blank newName.
    fun updateDish(dishId: Int, newName: String) {
        if (newName.isBlank()) return
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) dish.copy(name = newName.trim()) else dish
            }
        }
    }

    // DELETE -- TODO 2 (5 pts)
    // Remove the dish whose id == dishId.
    fun deleteDish(dishId: Int) {
        _dishes.update { list ->
            list.filter { it.id != dishId }
        }
    }

    // ---------------- RECIPE CRUD ----------------

    // CREATE -- TODO 3 (5 pts)
    // Add a new Recipe(id = nextId++, text = text.trim()) to the dish
    // whose id == dishId. Ignore a blank text.
    fun addRecipe(dishId: Int, text: String) {
        if (text.isBlank()) return
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) {
                    val newRecipe = Recipe(id = nextId++, text = text.trim())
                    dish.copy(recipes = dish.recipes + newRecipe)
                } else dish
            }
        }
    }

    // UPDATE -- TODO 4 (5 pts)
    // Change the text of the recipe whose id == recipeId, inside the
    // dish whose id == dishId. This needs a map() INSIDE a map().
    fun updateRecipe(dishId: Int, recipeId: Int, newText: String) {
        if (newText.isBlank()) return
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(recipes = dish.recipes.map { recipe ->
                        if (recipe.id == recipeId) recipe.copy(text = newText.trim()) else recipe
                    })
                } else dish
            }
        }
    }

    // DELETE -- TODO 5 (5 pts)
    // Remove the recipe whose id == recipeId from the dish whose id == dishId.
    fun deleteRecipe(dishId: Int, recipeId: Int) {
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(recipes = dish.recipes.filter { it.id != recipeId })
                } else dish
            }
        }
    }
}
