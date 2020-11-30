package mx.ine.institucional.movil.arquitecturamvvm.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import mx.ine.institucional.movil.arquitecturamvvm.data.local.LocalDataSource
import mx.ine.institucional.movil.arquitecturamvvm.data.local.LocalDataSourceImpl
import mx.ine.institucional.movil.arquitecturamvvm.data.remote.RemoteDataSource
import mx.ine.institucional.movil.arquitecturamvvm.data.remote.RemoteDataSourceImpl
import mx.ine.institucional.movil.arquitecturamvvm.data.repository.RecipeRepository
import mx.ine.institucional.movil.arquitecturamvvm.data.repository.RecipeRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    abstract fun bindDataSourceImpl(dataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}