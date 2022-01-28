package com.ojanbelajar.aronta.utils

import android.util.Log
import com.ojanbelajar.aronta.data.source.local.entity.*
import com.ojanbelajar.aronta.data.source.remote.response.*

object DataMapper {
    fun mapNewsResponseToNewsEntity(input: NewsResponse): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.news.map {
            val news = NewsEntity(
                id = it._id,
                judul = it.title,
                konten = it.content,
                picture = it.picture,
                link = it.link
            )
            newsList.add(news)
        }
        return newsList
    }
    fun mapLessonResponseToLearnEntity(input: LessonResponse): List<LearnEntity> {
        val lessonList = ArrayList<LearnEntity>()
        input.lessons.map {
            val learn = LearnEntity(
                id = it._id,
                judul = it.title,
                konten = it.content,
                picture = it.picture,
                link = it.link
            )
            lessonList.add(learn)
        }
        return lessonList
    }

    fun mapOrderFullResponseToOrderResponse(input: OrderFullResponse): List<OrderEntity> {
        val orderList = ArrayList<OrderEntity>()
        input.orders.map {
            val order = OrderEntity(
                _id = it._id,
                type = it.type,
                workingHours = it.workingHours,
                price = it.price,
                status = it.status,
                farmer = FarmerEntity("",it.farmer.name,"",it.farmer.phoneNumber,it.farmer.address),
                worker = BuruhEntity(it.worker._id,it.worker.name,"",it.worker.phoneNumber,it.worker.hourlyPrice.toString()),
                orderDate = it.orderDate
            )
            orderList.add(order)
        }
        return orderList
    }

    fun mapWorkerResponseToBuruhEntity(input: WorkerFullResponse): List<BuruhEntity> {
        val buruhList = ArrayList<BuruhEntity>()
        input.worker.map {
            val learn = BuruhEntity(
                id = it._id,
                nama = it.name,
                email = it.user.email,
                telepon = it.phoneNumber,
                harga = it.hourlyPrice.toString(),
                keahlian = it.skill,
                workhour = it.totalWorkingHours.toString(),
                rating = it.rating,
                image = it.picture,
                tipe = it.type
            )
            buruhList.add(learn)
        }
        return buruhList
    }
}