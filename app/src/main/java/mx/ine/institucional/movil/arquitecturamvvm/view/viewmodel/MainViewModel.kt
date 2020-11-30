package mx.ine.institucional.movil.arquitecturamvvm.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.ine.institucional.movil.arquitecturamvvm.base.Event
import mx.ine.institucional.movil.arquitecturamvvm.base.Resource
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Category
import mx.ine.institucional.movil.arquitecturamvvm.data.model.CategoryRecipe
import mx.ine.institucional.movil.arquitecturamvvm.data.model.Meal
import mx.ine.institucional.movil.arquitecturamvvm.data.model.MealEntity
import mx.ine.institucional.movil.arquitecturamvvm.di.IoDispatcher
import mx.ine.institucional.movil.arquitecturamvvm.usecases.RecipeUseCases

class MainViewModel @ViewModelInject constructor(
    private val recipeUseCase: RecipeUseCases,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _allCat = MutableLiveData<String>()

    private fun setAllCat(string: String) {
        _allCat.value = string
    }

    init {
        setAllCat("all")
    }


    private val _getCategoryListRecipe = MutableLiveData<Event<Resource<List<CategoryRecipe>>>>()
    val getCategoryListRecipe: LiveData<Event<Resource<List<CategoryRecipe>>>> = _getCategoryListRecipe

    val fetchCategoryListRecipe =
        _allCat.distinctUntilChanged().switchMap {
//            EspressoIdlingResource.increment()
            liveData(viewModelScope.coroutineContext + ioDispatcher) {
                val responseLoading = Event(Resource.loading(null))
                _getCategoryListRecipe.postValue(responseLoading)
                emit(responseLoading)
                try {
                    val responseSuccess = Event(recipeUseCase.getCategoriesList()!!)
                    _getCategoryListRecipe.postValue(responseSuccess)
//                    EspressoIdlingResource.decrement()
                    emit(responseSuccess)
                } catch (e: Exception) {
                    val responseError = Event(Resource.error(e.message.toString(), null))
                    _getCategoryListRecipe.postValue(responseError)
//                    EspressoIdlingResource.decrement()
                    emit(responseError)
                }
            }
        }

    private val _getCategoriesByName = MutableLiveData<Event<Resource<List<Category>>>>()
    val getCategoriesByName: LiveData<Event<Resource<List<Category>>>> = _getCategoriesByName

    fun fetchCategoriesByName(_nameOfCategoryData: String) =
        liveData(viewModelScope.coroutineContext + ioDispatcher) {
//            EspressoIdlingResource.increment()
            val responseLoading = Event(Resource.loading(null))
            _getCategoriesByName.postValue(responseLoading)
            emit(responseLoading)
            try {
                val responseSuccess =
                    Event(recipeUseCase.getMealListByCategoryName(_nameOfCategoryData))
                _getCategoriesByName.postValue(responseSuccess)
//                EspressoIdlingResource.decrement()
                emit(responseSuccess)
            } catch (e: Exception) {
                val responseError = Event(Resource.error(e.message.toString(), null))
                _getCategoriesByName.postValue(responseError)
//                EspressoIdlingResource.decrement()
                emit(responseError)
            }
        }

    private val _getMealById = MutableLiveData<Event<Resource<List<Meal>>>>()
    val getMealById: LiveData<Event<Resource<List<Meal>>>> = _getMealById

    fun fetchMealById(category: Category) =
        liveData(viewModelScope.coroutineContext + ioDispatcher) {
//            EspressoIdlingResource.increment()
            val responseLoading = Event(Resource.loading(null))
            _getMealById.postValue(responseLoading)
            emit(responseLoading)
            try {
                val responseSuccess = Event(recipeUseCase.getMealListById(category.idMealCategory))
                _getMealById.postValue(responseSuccess)
//                EspressoIdlingResource.decrement()
                emit(responseSuccess)
            } catch (e: Exception) {
                val responseError = Event(Resource.error(e.message.toString(), null))
                _getMealById.postValue(responseError)
//                EspressoIdlingResource.decrement()
                emit(responseError)
            }
        }

    fun saveMealFavoriteIntoDb(meal: MealEntity) {
//        EspressoIdlingResource.increment()
        viewModelScope.launch {
            recipeUseCase.saveFavoriteMeal(meal)
//            EspressoIdlingResource.decrement()
        }
    }

    private val _insertFavoriteItemStatus = MutableLiveData<Event<Resource<MealEntity>>>()
    val insertFavoriteItemStatus: LiveData<Event<Resource<MealEntity>>> = _insertFavoriteItemStatus

    fun saveMealFavorite(meal: MealEntity) {
        if (meal.idMeal.isEmpty() ||
            meal.nameMeal.isEmpty() ||
            meal.categoryMeal.isEmpty() ||
            meal.imageMealUrl.isEmpty()
        ) {
            _insertFavoriteItemStatus.value =
                Event(Resource.error("Error data is not completed", null))
            return
        } else {
            saveMealFavoriteIntoDb(meal)
            _insertFavoriteItemStatus.value = Event(Resource.success(meal))
        }
    }

    private val _getFavoritesItemData = MutableLiveData<Event<Resource<List<Meal>>>>()
    val getFavoritesItemData: LiveData<Event<Resource<List<Meal>>>> = _getFavoritesItemData

    fun getAllFavorites() = liveData(viewModelScope.coroutineContext + ioDispatcher) {
//        EspressoIdlingResource.increment()
        val responseLoading = Event(Resource.loading(null))
        _getFavoritesItemData.postValue(responseLoading)
        emit(responseLoading)
        try {
            val responseSuccess = Event(recipeUseCase.getAllFavoriteMeal())
            _getFavoritesItemData.postValue(responseSuccess)
//            EspressoIdlingResource.decrement()
            emit(responseSuccess)
        } catch (e: Exception) {
            val responseError = Event(Resource.error(e.message.toString(), null))
            _getFavoritesItemData.postValue(responseError)
//            EspressoIdlingResource.decrement()
            emit(responseError)
        }
    }

    private val _getUpdateFavoritesData = MutableLiveData<Event<Resource<List<Meal>>>>()
    val getUpdateFavoritesData: LiveData<Event<Resource<List<Meal>>>> = _getUpdateFavoritesData

    fun deleteMealFavorite(meal: MealEntity) =
        liveData(viewModelScope.coroutineContext + ioDispatcher) {
//            EspressoIdlingResource.increment()
            val responseLoading = Event(Resource.loading(null))
            _getUpdateFavoritesData.postValue(responseLoading)
            emit(responseLoading)
            try {
                val responseSuccess = Event(recipeUseCase.deleteFavoriteMeal(meal))
                _getUpdateFavoritesData.postValue(responseSuccess)
//                EspressoIdlingResource.decrement()
                emit(responseSuccess)
            } catch (e: Exception) {
                val responseError = Event(Resource.error(e.message.toString(), null))
                _getUpdateFavoritesData.postValue(responseError)
//                EspressoIdlingResource.decrement()
                emit(responseError)
            }
        }

}