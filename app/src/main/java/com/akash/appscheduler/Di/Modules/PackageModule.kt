package com.akash.appscheduler.Di.Modules

import com.akash.appscheduler.Repositories.DataSource.PackageDataSource
import com.akash.appscheduler.Repositories.DataSourceImpl.PackageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class PackageManagerDataSource



@InstallIn(SingletonComponent::class)
@Module
abstract class PackageModule {

    @PackageManagerDataSource
    @Singleton
    @Binds
    abstract fun bindInMemoryLogger(impl: PackageDataSourceImpl): PackageDataSource
}