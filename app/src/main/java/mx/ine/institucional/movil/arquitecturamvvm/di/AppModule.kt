package mx.ine.institucional.movil.arquitecturamvvm.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.ine.institucional.movil.arquitecturamvvm.data.local.AppDatabase
import mx.ine.institucional.movil.arquitecturamvvm.data.remote.WebService
import mx.ine.institucional.movil.arquitecturamvvm.utils.AppConstants.BASE_URL
import mx.ine.institucional.movil.arquitecturamvvm.utils.AppConstants.DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDatabase) = db.recipeDao()

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebServis(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)
}