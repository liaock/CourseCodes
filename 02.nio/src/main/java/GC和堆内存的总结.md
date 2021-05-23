作业2：写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

1. 串行垃圾回收GC日志命令：java -XX:+UseSerialGC -Xms128m -Xmx128m -XX:+PrintGCDetails GCLogAnalysis
2. 并行垃圾回收GC日志命令：java -XX:+UseParallelGC -Xms128m -Xmx128m -XX:+PrintGCDetails GCLogAnalysis
3. CMS GC日志命令：java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -XX:+PrintGCDetails GCLogAnalysis
4. G1 GC命令：java -XX:+UseG1GC -Xms128m -Xmx128m -XX:+PrintGC GCLogAnalysis

串行、并行、CMS、G1 分配JVM堆内存初始值以及最大值为从128M、256M、512M到1G、2G逐渐增大

- 发现堆内存越小无论是youngGC还是fullGC 都执行的越频繁，其中当内存为128M时，还出现了OOM，说明内存分配不合理（太小），容易触发GC的执行，严重影响系统性能；当内存分配率 > 回收率时，应该保持警惕，避免产生oom

- 并行GC的时间几乎是串行GC时间的一半
- 内存越大，G1垃圾回收性能越高
- cms垃圾回收的吞吐量相对于其他回收器比较差

另外，测试中发现 youngGC的次数在未执行15次的情况下， 已经触发了fullGC，按道理说，新生代的对象默认情况下只有在15次GC后还未被清理的情况下才会晋升老年代，查了下资料，发现对象进入老年代的情况有4种，这里mark一下

- 大对象
- 长期存活的对象，通过参数-XX:MaxTenuringThreshold设置，默认15岁
- 动态对象年龄判定，如果在Survivor空间中相同年龄的所有对象大小的总和大于Survivor空间的一半,年龄大于或等于年龄的对象就可以直接进入老年代,无须等到MaxTenuringThreshold中要求的年龄。
- 在一次安全Minor GC 中，仍然存活的对象不能在另一个Survivor 完全容纳，则会通过担保机制进入老年代。 