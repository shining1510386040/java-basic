# java-basic
Java基础的一些测试：
例如 java8 的功能；
多线程
集合
IO

and so on
# Java中的集合？
1.核心接口：
    Iterator（迭代器接口）
    Iterable(可迭代接口) -> Collection(集合接口)-> List(列表，线性表接口) :ArrayList、LinkedList
                                               -> Set(元素不重复的集合接口)：HashSet、TreeSet
                                               -> Queue(队列)：    ArrayBlockingQueue、LinkedBlockingQueue
                                                    -> Deque(双端队列)：LinkedBlockingDeque
    
    Map（散列接口）-> SortedMap（可排序的map）
    ==============>>接口方法？
    1.1Queue队列接口：
        入队：add() offer()
        出队：remove() peek()
        获取队头元素：element()
    1.2Collection集合接口：
        添加元素：    add
        添加多个元素：addAll
        清空集合：clear
        判断是否包含制定元素：contains
        判断一个集合是不是另一个集合的子集：containsAll
        判断值是否相等：equals
        获取hashCode值：hashCode
        判断集合是否为空：isEmpty
        获取迭代器：iterator
        获取并行流：parallelStream
        删除指定元素：remove
        求差集？：removeAll
        ？：removeIf
        求？retainAll
        获取集合元素个数：size
        spliterator
        获取串行流：stream
        转化为数组：toArray
    1.3 Map散列接口：
        内部类：Entry
        清空map:clear
            默认方法？：compute
            computeIfAbsent
            computeIfPresent
        判断一个key是否存在：containsKey
        判断一个值是否存在：containsValue
        获取entry的Set集合：entrySet
        判断是否相等：equals
        默认方法，增强for处理：forEach
        获取一个key的值：get
        getOrDefault
        获取hashCode值：hashCode
        判断是否为空：isEmpty
        获取key的set集合：keySet
            合并map？？：merge
        添加一个k-v：put
        添加一个Map：putAll
            putIfAbsent
        删除指定key：remove
        替换key的value值：replace
        获取map的大小： size
        获取map中v的collection集合：values
    =============》》主要集合
    HashMap：非线成安全的，数组+链表+红黑树的数据结构实现的；当hash桶中 链表的长度大于8时会转化为红黑树；扩容时会发生重新hash，计算元素的存储位置；
    HashTable:
    TreeMap:
    ArrayList：数组方式实现的线性表，初始化时最好制定容量大小，扩容时会增长为原数组长度的2倍；
    LinkedList:链表方式实现的线性表，同时兼备双端队列的功能；
    LinkedBlockingQueue：链表方式实现的阻塞队列，
    ArrayBlockingQueue:数组方式实现的阻塞队列；
    ConcurrentHashMap:线程安全的Map集合，给每个hash桶加锁，减小锁的粒度；
    ==================>>>关于集合的工具类？
    Arrays：数组工具
    
     转化为List集合：   asList
     二分查找：   binarySearch
        binarySearch0
        copyOf
        copyOfRange
        deepEquals
        deepEquals0
        deepHashCode
        deepToString
     判断是否相等：   equals
      填充  fill
        hashCode
        legacyMergeSort
        mergeSort
        parallelPrefix
        parallelSetAll
        parallelSort
        rangeCheck
        setAll
      排序：  sort
        spliterator
        stream
        swap
        toString
    
    Collections:集合工具
     添加集合：   addAll
        asLifoQueue
      二分查找：  binarySearch
        
        checkedCollection
        checkedList
        checkedMap
        checkedNavigableMap
        checkedNavigableSet
        checkedQueue
        checkedSet
        checkedSortedMap
        checkedSortedSet
     复制：   copy
        disjoint
        emptyEnumeration
        emptyIterator
        emptyList
        emptyListIterator
        emptyMap
        emptyNavigableMap
        emptyNavigableSet
        emptySet
        emptySortedMap
        emptySortedSet
        enumeration
        eq
        fill
        frequency
        get
        indexedBinarySearch
        indexedBinarySearch
        indexOfSubList
        iteratorBinarySearch
        iteratorBinarySearch
        lastIndexOfSubList
        list
      取最大值：  max
      取最小值：  min
        min
        nCopies
        newSetFromMap
        replaceAll
      反转：  reverse
        reverseOrder
        reverseOrder
        rotate
        rotate1
        rotate2
        shuffle
        shuffle
        singleton
        singletonIterator
        singletonList
        singletonMap
        singletonSpliterator
        sort
        sort
        swap
        swap
        synchronizedCollection
        synchronizedCollection
        synchronizedList
        synchronizedList
        synchronizedMap
        synchronizedNavigableMap
        synchronizedNavigableSet
        synchronizedSet
        synchronizedSet
        synchronizedSortedMap
        synchronizedSortedSet
        unmodifiableCollection
        unmodifiableList
        unmodifiableMap
        unmodifiableNavigableMap
        unmodifiableNavigableSet
        unmodifiableSet
        unmodifiableSortedMap
        unmodifiableSortedSet
        zeroLengthArray