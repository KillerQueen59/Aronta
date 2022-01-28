package com.ojanbelajar.aronta.data.source.local

import com.ojanbelajar.aronta.data.source.local.room.ArontaDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource  @Inject constructor (private val dao: ArontaDao):LocalDataInterface {



}