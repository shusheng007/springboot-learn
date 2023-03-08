    @Bean
    public TaskExecutor threadPoolExecutorCpu(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(2);
        executor.setKeepAliveSeconds(1);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("task-thread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        executor.initialize();
        return executor;
    }

```aidl
    @GetMapping("/run-async")
    public String runAsync(@RequestParam("count") Integer count){
        List<Integer> collect = IntStream.rangeClosed(1, count).boxed().collect(Collectors.toList());

        for (int i : collect) {
            new Thread(() -> concurrencyService.runAsync(i)).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                log.error("error",e);
            }
        }
        return "ok";
    }
```

AbortPolicy：

023-03-07 16:59:36.944 INFO 17512 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:42,num:1
2023-03-07 16:59:37.144 INFO 17512 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:44,num:2
2023-03-07 16:59:37.819 INFO 17512 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : start:48,num:5
2023-03-07 16:59:39.975 INFO 17512 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:42,num:1
2023-03-07 16:59:39.976 INFO 17512 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:42,num:3
2023-03-07 16:59:40.158 INFO 17512 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:44,num:2
2023-03-07 16:59:40.158 INFO 17512 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:44,num:4
2023-03-07 16:59:40.820 INFO 17512 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : end:48,num:5
2023-03-07 16:59:42.988 INFO 17512 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:42,num:3
2023-03-07 16:59:43.169 INFO 17512 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:44,num:4

2023-03-07 17:16:31.478 INFO 18636 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:42,num:1
2023-03-07 17:16:31.683 INFO 18636 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:44,num:2
2023-03-07 17:16:32.313 INFO 18636 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : start:48,num:5
Exception in thread "Thread-12" org.springframework.core.task.TaskRejectedException: Executor [
java.util.concurrent.ThreadPoolExecutor@68e7e518[Running, pool size = 3, active threads = 3, queued tasks = 2, completed tasks = 0]]
did not accept task:
org.springframework.aop.interceptor.AsyncExecutionInterceptor$$Lambda$743/0x00000008004c3840@29daef23
at org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor.submit(ThreadPoolTaskExecutor.java:391)
at org.springframework.aop.interceptor.AsyncExecutionAspectSupport.doSubmit(AsyncExecutionAspectSupport.java:292)
at org.springframework.aop.interceptor.AsyncExecutionInterceptor.invoke(AsyncExecutionInterceptor.java:129)
at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)
at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)
at top.shusheng007.composite.concurrency.ConcurrencyService$$EnhancerBySpringCGLIB$$bf1688fd.runAsync(<generated>)
at top.shusheng007.composite.controller.CompositeController$1.run(CompositeController.java:88)
at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.util.concurrent.RejectedExecutionException: Task
java.util.concurrent.FutureTask@2de56ec0[Not completed, task = org.springframework.aop.interceptor.AsyncExecutionInterceptor$$Lambda$743/0x00000008004c3840@29daef23]
rejected from
java.util.concurrent.ThreadPoolExecutor@68e7e518[Running, pool size = 3, active threads = 3, queued tasks = 2, completed tasks = 0]
at java.base/java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2055)
at java.base/java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:825)
at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1355)
at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:140)
at org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor.submit(ThreadPoolTaskExecutor.java:388)
... 8 more
2023-03-07 17:16:34.491 INFO 18636 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:42,num:1
2023-03-07 17:16:34.491 INFO 18636 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:42,num:3
2023-03-07 17:16:34.689 INFO 18636 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:44,num:2
2023-03-07 17:16:34.690 INFO 18636 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:44,num:4
2023-03-07 17:16:35.324 INFO 18636 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : end:48,num:5
2023-03-07 17:16:37.503 INFO 18636 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:42,num:3
2023-03-07 17:16:37.700 INFO 18636 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:44,num:4

DiscardOldestPolicy：

2023-03-07 17:39:59.405 INFO 3344 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:56,num:1
2023-03-07 17:39:59.600 INFO 3344 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:58,num:2
2023-03-07 17:40:00.214 INFO 3344 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : start:62,num:5
2023-03-07 17:40:02.414 INFO 3344 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:56,num:1
2023-03-07 17:40:02.414 INFO 3344 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:56,num:4
2023-03-07 17:40:02.610 INFO 3344 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:58,num:2
2023-03-07 17:40:02.611 INFO 3344 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:58,num:6
2023-03-07 17:40:03.226 INFO 3344 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : end:62,num:5
2023-03-07 17:40:05.421 INFO 3344 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:56,num:4
2023-03-07 17:40:05.616 INFO 3344 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:58,num:6

CallerRunsPolicy:

2023-03-07 17:40:58.578 INFO 19116 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:53,num:1
2023-03-07 17:40:58.739 INFO 19116 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:55,num:2
2023-03-07 17:40:59.366 INFO 19116 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : start:59,num:5
2023-03-07 17:40:59.580 INFO 19116 --- [      Thread-12] t.s.c.concurrency.ConcurrencyService     : start:60,num:6
2023-03-07 17:41:01.587 INFO 19116 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:53,num:1
2023-03-07 17:41:01.587 INFO 19116 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : start:53,num:3
2023-03-07 17:41:01.753 INFO 19116 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:55,num:2
2023-03-07 17:41:01.753 INFO 19116 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : start:55,num:4
2023-03-07 17:41:02.375 INFO 19116 --- [  task-thread-3] t.s.c.concurrency.ConcurrencyService     : end:59,num:5
2023-03-07 17:41:02.587 INFO 19116 --- [      Thread-12] t.s.c.concurrency.ConcurrencyService     : end:60,num:6
2023-03-07 17:41:04.589 INFO 19116 --- [  task-thread-1] t.s.c.concurrency.ConcurrencyService     : end:53,num:3
2023-03-07 17:41:04.756 INFO 19116 --- [  task-thread-2] t.s.c.concurrency.ConcurrencyService     : end:55,num:4
