/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.manager;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.management.MonitorInfo;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.management.OperatingSystemMXBean;

/**
 * 描述：
 * 
 * @author hanlin.yq
 * 
 * @since V0.1 2013-4-27下午10:47:35
 */
public class SystemShow {
	private static final Logger logger = LoggerFactory
			.getLogger(SystemShow.class);

	/**
	 * 描述：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SystemShow systemShow = new SystemShow();
		systemShow.showJvm();
	}

	public void showJvm() {
		System.out
				.println("=======================通过java来获取相关系统状态============================ ");
		int i = (int) Runtime.getRuntime().totalMemory() / 1024;// Java
																// 虚拟机中的内存总量,以字节为单位
		System.out.println("总的内存量 i is " + i);
		int j = (int) Runtime.getRuntime().freeMemory() / 1024;// Java
																// 虚拟机中的空闲内存量
		System.out.println("空闲内存量 j is " + j);
		System.out.println("最大内存量 is " + Runtime.getRuntime().maxMemory()
				/ 1024);
		System.out
				.println("=======================OperatingSystemMXBean============================ ");
		OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();
		System.out.println(osm.getFreeSwapSpaceSize() / 1024);
		System.out.println(osm.getFreePhysicalMemorySize() / 1024);
		System.out.println(osm.getTotalPhysicalMemorySize() / 1024);
		// 获取操作系统相关信息
		System.out.println("osm.getArch() " + osm.getArch());
		System.out.println("osm.getAvailableProcessors() "
				+ osm.getAvailableProcessors());
		System.out.println("osm.getCommittedVirtualMemorySize() "
				+ osm.getCommittedVirtualMemorySize());
		System.out.println("osm.getName() " + osm.getName());
		System.out
				.println("osm.getProcessCpuTime() " + osm.getProcessCpuTime());
		System.out.println("osm.getVersion() " + osm.getVersion());
		// 获取整个虚拟机内存使用情况
		System.out
				.println("=======================MemoryMXBean============================ ");
		MemoryMXBean mm = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
		System.out.println("getHeapMemoryUsage " + mm.getHeapMemoryUsage());
		System.out.println("getNonHeapMemoryUsage "
				+ mm.getNonHeapMemoryUsage());
		// 获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
		System.out
				.println("=======================ThreadMXBean============================ ");
		ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
		System.out.println("getThreadCount " + tm.getThreadCount());
		System.out.println("getPeakThreadCount " + tm.getPeakThreadCount());
		System.out.println("getCurrentThreadCpuTime "
				+ tm.getCurrentThreadCpuTime());
		System.out.println("getDaemonThreadCount " + tm.getDaemonThreadCount());
		System.out.println("getCurrentThreadUserTime "
				+ tm.getCurrentThreadUserTime());
		// 当前编译器情况
		System.out
				.println("=======================CompilationMXBean============================ ");
		CompilationMXBean gm = (CompilationMXBean) ManagementFactory
				.getCompilationMXBean();
		System.out.println("getName " + gm.getName());
		System.out.println("getTotalCompilationTime "
				+ gm.getTotalCompilationTime());
		// 获取多个内存池的使用情况
		System.out
				.println("=======================MemoryPoolMXBean============================ ");
		List<MemoryPoolMXBean> mpmList = ManagementFactory
				.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mpm : mpmList) {
			System.out.println("getUsage " + mpm.getUsage());
			System.out.println("getMemoryManagerNames "
					+ mpm.getMemoryManagerNames().toString());
		}
		// 获取GC的次数以及花费时间之类的信息
		System.out
				.println("=======================MemoryPoolMXBean============================ ");
		List<GarbageCollectorMXBean> gcmList = ManagementFactory
				.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean gcm : gcmList) {
			System.out.println("getName " + gcm.getName());
			System.out
					.println("getMemoryPoolNames " + gcm.getMemoryPoolNames());
		}
		// 获取运行时信息
		System.out
				.println("=======================RuntimeMXBean============================ ");
		RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory
				.getRuntimeMXBean();
		System.out.println("getClassPath " + rmb.getClassPath());
		System.out.println("getLibraryPath " + rmb.getLibraryPath());
		System.out.println("getVmVersion " + rmb.getVmVersion());

		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("java.vendor"));
		System.out.println(System.getProperty("java.vendor.url"));
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.vm.specification.version"));
		System.out.println(System.getProperty("java.vm.specification.vendor"));
		System.out.println(System.getProperty("java.vm.specification.name"));
		System.out.println(System.getProperty("java.vm.version"));
		System.out.println(System.getProperty("java.vm.vendor"));
		System.out.println(System.getProperty("java.vm.name"));
		System.out.println(System.getProperty("java.specification.version"));
		System.out.println(System.getProperty("java.specification.vendor"));
		System.out.println(System.getProperty("java.specification.name"));
		System.out.println(System.getProperty("java.class.version"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(System.getProperty("java.compiler"));
		System.out.println(System.getProperty("java.ext.dirs"));

		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("file.separator"));
		System.out.println(System.getProperty("path.separator"));
		System.out.println(System.getProperty("line.separator"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir"));
	}

	public static void getVMClassInformation() {
		ClassLoadingMXBean classLoadMXBean = ManagementFactory
				.getClassLoadingMXBean();
		int loadClazzCount = classLoadMXBean.getLoadedClassCount();
		System.out.println("加载类的数量：" + loadClazzCount);
		long hasloadClazzCount = classLoadMXBean.getTotalLoadedClassCount();
		System.out.println("已经加载类的数量：" + hasloadClazzCount);
		long hasUnloadClazzCount = classLoadMXBean.getUnloadedClassCount();
		System.out.println("尚未加载类的数量：" + hasUnloadClazzCount);
		boolean isVerbose = classLoadMXBean.isVerbose();
		System.out.println("是否开始加载类信息：" + isVerbose);
		// CompilationMXBean 帮助用户了解当前的编译器和编译情况，该 mxbean 提供的信息不多。
		CompilationMXBean compilationMXBean = ManagementFactory
				.getCompilationMXBean();
		String jitName = compilationMXBean.getName();
		System.out.println("即时编译的名称：" + jitName);
		long totalCompileTime = compilationMXBean.getTotalCompilationTime();
		System.out.println("总的编译时间：" + totalCompileTime + "/s");
		boolean isSupport = compilationMXBean
				.isCompilationTimeMonitoringSupported();
		if (isSupport) {
			System.out.println("支持即时编译器编译监控");
		} else {
			System.out.println("不支持即时编译器编译监控");
		}
		List<GarbageCollectorMXBean> gcMXBeanList = ManagementFactory
				.getGarbageCollectorMXBeans();
		// 相对于开放人员对 GC 的关注程度来说，该 mxbean 提供的信息十分有限，仅仅提供了 GC 的次数和 GC 花费总时间的近似值。
		for (GarbageCollectorMXBean gcMXBean : gcMXBeanList) {
			// 内存池名称
			String[] poolNames = gcMXBean.getMemoryPoolNames();
			for (String poolName : poolNames) {
				System.out.println("poolNames=" + poolName);
			}
		}
		// 提供了内存管理类和内存池（memory pool）的名字信息。
		List<MemoryManagerMXBean> memoryMgrMXBeanList = ManagementFactory
				.getMemoryManagerMXBeans();
		// 内存管理器的信息
		for (MemoryManagerMXBean memoryManagerMXBean : memoryMgrMXBeanList) {
			String[] poolNames = memoryManagerMXBean.getMemoryPoolNames();
			for (String poolName : poolNames) {
				System.out.println("poolNames=" + poolName);
			}
		}
		// 内存信息
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		// java堆得使用情况信息
		MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
		long usaged = heapMemoryUsage.getUsed();
		System.out.println("java 内存堆使用内存：" + usaged);
		long maxUsage = heapMemoryUsage.getMax();
		System.out.println("java 内存堆最大使用内存：" + maxUsage);
		long initUsage = heapMemoryUsage.getInit();
		System.out.println("java 内存堆初始化时占用内存：" + initUsage);
		List<MemoryPoolMXBean> memoryPoolMXBeanList = ManagementFactory
				.getMemoryPoolMXBeans();
		// 该信息提供了大量的信息。在 JVM 中，可能有几个内存池，因此有对应的内存池信息，因此，在工厂类中
		// ，getMemoryPoolMXBean() 得到是一个 MemoryPoolMXBean 的 list。每一个
		// MemoryPoolMXBean 都包含
		// 了该内存池的详细信息，如是否可用、当前已使用内存 / 最大使用内存值、以及设置最大内存值等等。
		for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeanList) {
			// 内存池的名称
			String poolName = memoryPoolMXBean.getName();
			// 内存管理器的名称
			String[] memoryMgrNames = memoryPoolMXBean.getMemoryManagerNames();
			for (String mgrName : memoryMgrNames) {
				System.out.println("内存管理器的名称：" + mgrName);
			}
			// java JVM最近内存的使用情况
			MemoryUsage memoryUsage = memoryPoolMXBean.getCollectionUsage();
			System.out.println("内存池的收集器内存使用率：" + memoryUsage.getUsed()
					/ memoryUsage.getMax() + "%");
			memoryPoolMXBean.getCollectionUsageThreshold();
			memoryPoolMXBean.getCollectionUsageThresholdCount();
			MemoryType memoryType = memoryPoolMXBean.getType();
			System.out.println("内存的信息：" + memoryType.name());
			MemoryUsage memoryUage = memoryPoolMXBean.getUsage();
			System.out.println("内存池的内存使用率：" + memoryUage.getUsed()
					/ memoryUage.getMax() + "%");
			memoryPoolMXBean.getUsageThreshold();
			System.out.println();
			memoryPoolMXBean.getUsageThresholdCount();
			System.out.println();
		}
		// 该类提供的是操作系统的简单信息，如构架名称、当前 CPU 数、最近系统负载等。
		java.lang.management.OperatingSystemMXBean operateSystemMBean = ManagementFactory
				.getOperatingSystemMXBean();
		String operateName = operateSystemMBean.getName();
		System.out.println("操作系统的名称：" + operateName);
		int processListCount = operateSystemMBean.getAvailableProcessors();
		System.out.println("操作系统的进程数：" + processListCount);
		String osArchName = operateSystemMBean.getArch();// System.getProperty("os.arch");
		System.out.println("操作系统的架构：" + osArchName);
		double loadAverage = operateSystemMBean.getSystemLoadAverage();
		System.out.println("操作系统的负载均衡信息：" + loadAverage);
		String versionName = operateSystemMBean.getVersion();// System.getProperty("os.version");
		System.out.println("操作系统的版本号码：" + versionName);
		// 运行时信息包括当前虚拟机的名称、提供商、版本号，以及 classpath、bootclasspath 和系统参数等等。
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		String vmName = runtimeMXBean.getVmName();
		System.out.println("虚拟机的名称：" + vmName);
		String vmVersion = runtimeMXBean.getVmVersion();
		System.out.println("虚拟机的版本：" + vmVersion);
		Map<String, String> sysMaps = runtimeMXBean.getSystemProperties();
		Set<Entry<String, String>> keyLists = sysMaps.entrySet();
		for (Entry<String, String> entry : keyLists) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String vmVendor = runtimeMXBean.getVmVendor();
		System.out.println("系统的供应商的名称：" + vmVendor);
		// 类加载器的路径
		String clazzPath = runtimeMXBean.getClassPath();// System.getProperty("java.class.path")}
		System.out.println("操作系统的类加载器的名称：" + clazzPath);
		List<String> argsList = runtimeMXBean.getInputArguments();
		System.out.println("操作系统的参数信息");
		for (String args : argsList) {
			System.out.println(" " + args);
		}
		String libPath = runtimeMXBean.getLibraryPath();// System.getProperty("java.library.path")
		System.out.println("java 的类库路径：" + libPath);
		String specVersion = runtimeMXBean.getManagementSpecVersion();
		System.out.println("实施运行Java虚拟机管理接口规范 的版本" + specVersion);
		String specName = runtimeMXBean.getSpecName();
		System.out.println("规范的名称：" + specName);
		String specVender = runtimeMXBean.getSpecVendor();
		System.out.println("规范管理接口供应商 ：" + specVender);
		long startTime = runtimeMXBean.getStartTime();
		System.out.println("java　虚拟机的开始启动的时间：" + startTime);
		runtimeMXBean.getSpecVersion();
		System.out.println("规范接口版本：：" + operateName);
		String bottClassPath = runtimeMXBean.getBootClassPath();
		System.out.println("操作系统的bootstrap 的classloader：" + bottClassPath);
		// 在 Java 这个多线程的系统中，对线程的监控是相当重要的。ThreadMXBean 就是起到这个作用。
		// ThreadMXBean 可以提供的信息包括各个线程的各种状态，CPU 占用情况，以及整个系统中的线
		// 程状况。从 ThreadMXBean 可以得到某一个线程的 ThreadInfo 对象。这个对象中则包含了这个
		// 线程的所有信息。
		// 线程的信息
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		// 所有的线程的编号
		long[] threadIds = threadMXBean.getAllThreadIds();
		for (long threadId : threadIds) {
			// 线程的信息
			ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
			// 线程被阻塞的数量
			threadInfo.getBlockedCount();
			// 被锁定线程的监控信息
			MonitorInfo[] monitorInfos = threadInfo.getLockedMonitors();
			for (MonitorInfo monitorInfo : monitorInfos) {
				int depth = monitorInfo.getLockedStackDepth();
				System.out.println("锁定的程度：" + depth);
			}
			// 异步锁定的信息
			LockInfo[] lockinfos = threadInfo.getLockedSynchronizers();
			// 锁定的信息
			for (LockInfo lockInfo : lockinfos) {
				System.out.println("锁定类的名称：" + lockInfo.getClassName());
			}
			// 线程的名称
			String threadName = threadInfo.getThreadName();
			System.out.println("线程的名称：" + threadName);
			Thread.State state = threadInfo.getThreadState();
			System.out.println("线程的信息：" + state.name());
		}
		long cpuTime = threadMXBean.getCurrentThreadCpuTime();
		long curentTime = threadMXBean.getCurrentThreadUserTime();
		long threadCount = threadMXBean.getDaemonThreadCount();
		long peakliveThreadCount = threadMXBean.getPeakThreadCount();
		long threadCounts = threadMXBean.getThreadCount();
		System.out.println("当前处于live状态的线程总的数量：" + threadCounts);
		long totalThreadCount = threadMXBean.getTotalStartedThreadCount();
		System.out.println("JVM 启动之后，总的自动线程数量：" + totalThreadCount);
	}

	/**
	 * 
	 * @return
	 */
	public static final String getHeapMemoryUsage() {
		ClassLoadingMXBean mxbean;
		return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()
				.getUsed()
				+ "/"
				+ ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()
						.getMax();
	}

	/**
	 * 当前使用内存的百分比
	 * 
	 * @return
	 */
	public static final String getHeapMemoryUsagePercent() {
		return (((double) ManagementFactory.getMemoryMXBean()
				.getHeapMemoryUsage().getUsed() / (double) ManagementFactory
				.getMemoryMXBean().getHeapMemoryUsage().getMax()) * 100L)
				+ "%";
	}

	/**
	 * 当前非java堆占用的百分比
	 * 
	 * @return
	 */
	public static final String getNonHeapMemoryUsage() {
		return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage()
				.getUsed()
				+ "/"
				+ ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage()
						.getMax();
	}

	/**
	 * 
	 * @return
	 */
	public static final String getNonHeapMemoryUsagePercent() {
		return (((double) ManagementFactory.getMemoryMXBean()
				.getNonHeapMemoryUsage().getUsed() / (double) ManagementFactory
				.getMemoryMXBean().getNonHeapMemoryUsage().getMax()) * 100)
				+ "%";
	}

	/**
	 * 获取线程数
	 * 
	 * @return
	 */
	public static final String getThreadCount() {
		return "" + ManagementFactory.getThreadMXBean().getThreadCount();
	}

}
