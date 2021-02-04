package com.example.xcloudkt.net.rx

import com.example.xcloudkt.net.except.ApiException
import com.example.xcloudkt.net.response.BaseBean
import com.example.xcloudkt.net.response.BaseResponseBean
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * 统一线程处理
 *虽然在Rxjava2中，Flowable是在Observable的基础上优化后的产物，Observable能解决的问题Flowable也都能解决，
 * 但是并不代表Flowable可以完全取代Observable,在使用的过程中，并不能抛弃Observable而只用Flowable。
 *由于基于Flowable发射的数据流，以及对数据加工处理的各操作符都添加了背压支持，附加了额外的逻辑，其运行效率要比Observable慢得多。
 *只有在需要处理背压问题时，才需要使用Flowable。
 */
object RxManage {

    /**
     * -----------------------------------------------不支持背压，通用返回bean处理，通过BaseResponseBean泛型实现实体类----------------------------------------------------
     * -----------------------------------------------不支持背压，通用返回bean处理，通过BaseResponseBean泛型实现实体类----------------------------------------------------
     * compose简化线程切换
     * @param <T>
     * @return
     * */
    fun <T> rxSchedulerObservableHelper(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     *继承baseBean处理
     * @param <T>
     * @return
     */
    fun <T> handleObservableResult(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.flatMap(Function<T, Observable<T>> { bean ->
                val baseBean = bean as BaseBean
                if (baseBean.code == 200) {
                    createObservable(bean)
                } else {
                    Observable.error(ApiException(baseBean.code, baseBean.msg))
                }
            })
        }
    }

    /**
     * 通过BaseResponseBean泛型实现实体类，不支持背压
     */
    fun <T> handleObservableResultT(): ObservableTransformer<BaseResponseBean<T>, T> {
        return ObservableTransformer { obs ->
            obs.flatMap { t: BaseResponseBean<T> ->
                createObservable(t.data)
//                if (t.code == 200) {
//                } else {
//                    Observable.error(ApiException(t.code, t.msg))
//                }
            }
        }
    }

    /**
     * 生成Observable,不支持背压
     * @param <T>
     * @return
     */
    private fun <T> createObservable(t: T): Observable<T> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    /**
     * ---------------------------------------------支持背压，继承baseBean，通用泛型----------------------------------------------------
     * ---------------------------------------------支持背压，继承baseBean，通用泛型----------------------------------------------------
     * compose简化线程切换,支持背压
     * @param <T>
     * @return
     */
    fun <T> rxSchedulerFlowableHelper(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


    /**
     *compose判断结果,统一返回结果处理,支持背压
     * @param <T>
     * @return
     */
    fun <T> handleFlowableResult(): FlowableTransformer<T, T> {
        return FlowableTransformer { followable ->
            followable.flatMap(Function<T, Flowable<T>> { bean ->
                val baseBean = bean as BaseBean
                if (baseBean.code == 200) {
                    createFlowable(bean)
                } else {
                    Flowable.error(ApiException(baseBean.code, baseBean.msg))
                }
            })
        }
    }

    /**
     *通过BaseResponseBean泛型实现实体类，支持背压
     * @param <T>
     * @return
     */
    fun <T> handleFlowableResultT(): FlowableTransformer<BaseResponseBean<T>, T> {
        return FlowableTransformer { followable ->
            followable.flatMap { bean ->
                if (bean.code == 200) {
                    createFlowable(bean.data)
                } else {
                    Flowable.error(ApiException(bean.code, bean.msg))
                }
            }
        }
    }

    /**
     * 生成Flowable,支持背压
     * @param <T>
     * @return
     */
    private fun <T> createFlowable(t: T): Flowable<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }

}
