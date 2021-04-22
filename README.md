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
# java中的IO和NIO
   1.阻塞IO：
       字节流：
       InputStream
           探测可用字节数available() :假设方法返回的int值为a，a代表的是在不阻塞的情况下，可以读入或者跳过（skip）的字节数。它一般用于在读入或者跳过之间先探测一下有多少可用字节。
           流读入一个字节read():读取输入流的下一个字节。这是一个抽象方法，不提供实现，子类必须实现这个方法。该方法读取下一个字节，返回一个0-255之间的int类型整数。如果到达流的末端，返回-1. 调用该方法的时候，方法阻塞直到出现下列其中一种情况：1）遇到流的尾部（end of the stream）。2）有数据可以读入。3）抛出异常。
           流读入多个字节read（byte[] b):试图读入多个字节，存入字节数组b，返回实际读入的字节数。
           流读入多个字节加偏移read (byte[] b, int off, int len)：读入的数据存储到b数组是从off开始。len是试图读入的字节数，返回的是实际读入的字节数。如果len=0，则什么也不读入，返回0；如果遇到流尾部，返回-1.否则至少读入一个字节。
           流跳过skip(long n):这个方法试图跳过当前流的n个字节，返回实际跳过的字节数。如果n为负数，返回0.
           做标记mark(int readlimit) ：这个方法用于在流的当前位置做个标记，参数readLimit指定这个标记的“有效期“，如果从标记处开始往后，已经获取或者跳过了readLimit个字节，那么这个标记失效，不允许再重新回到这个位置（通过reset方法）。也就是你想回头不能走得太远呀，浪子回头不一定是岸了，跳过（获取）了太多字 节，标记就不再等你啦。多次调用这个方法，前面的标记会被覆盖。
           定位到最近的标记reset():这个方法用于重定位到最近的标记。如果在这之前mark方法从来没被调用，或者标记已经无效，在抛出IOException
           检测当前流对象是否支持标记markSupported()：
           关闭流：close()
       OutputStream
               1、关闭操作（普通方法）：public void close() throws IOException
               2、刷新操作（普通方法）：public void flush() throws IOException
               3、写入整个数组（普通方法）：public void write(byte[] b) throws IOException
               4、（抽象方法）：public abstract void write(int b) throws IOException
               5、（普通方法）：public void write(byte[] b, int off, int len) throws IOException
       
       字符流：
       Reader
            abstract void	close()	关闭流并释放与其关联的所有系统资源。
            void	mark(int readAheadLimit)	标记流中的当前位置。
            boolean	markSupported()	判断此流是否支持mark（）操作。
            int	read()	读一个字符。
            int	read(char[] cbuf)	将字符读入数组。
            abstract int	read(char[] cbuf, int off, int len)	将字符读入数组的一部分。
            int	read(CharBuffer target)	尝试将字符读入指定的字符缓冲区。
            boolean	ready()	判断此流是否可以读取。
            void	reset()	重置流。
            long	skip(long n)	跳过字符。
       Writer
           Writer	apend(char c)	将指定的字符追加到此writer。
           Writer	append(CharSequence csq)	将指定的字符序列追加到此writer。
           Writer	append(CharSequence csq, int start, int end)	将指定字符序列的子序列追加到此writer。
           abstract void	close()	关闭流，先flush。
           abstract void	flush()	刷新流
           void	write(char[] cbuf)	写一个字符数组。
           abstract void	write(char[] cbuf, int off, int len)	写一个字符数组的一部分。
           void	write(int c)	写一个字符。
           void	write(String str)	写一个字符串。
           void	write(String str, int off, int len)	写一个字符串的一部分。
   2.非阻塞IO：
       通道Channel:
            isOpen()
            close)()
            
            FileChannel：用于从文件中读写数据
            DatagramChannel：能通过UDP读写网络中的数据
            SocketChannel：能通过TCP读写网络中的数据（客户端）
            ServerSocketChannel：可以监听新进来的TCP连接（服务端，像Web服务器那样）对每一个新进来的连接都会创建一个SocketChannel

       选择器Selector:
            open():创建一个Selector对象
            isOpen():是否是open状态，如果调用了close()方法则会返回false
            provider():获取当前Selector的Provider
            keys():获取当前channel注册在Selector上所有的key
            selectedKeys():获取当前channel就绪的事件列表
            selectNow():获取当前是否有事件就绪，该方法立即返回结果，不会阻塞；如果返回值>0，则代表存在一个或多个
            select(long timeout):selectNow的阻塞超时方法，超时时间内，有事件就绪时才会返回；否则超过时间也会返回
            select():selectNow的阻塞方法，直到有事件就绪时才会返回
            wakeup():调用该方法会时，阻塞在select()处的线程会立马返回；(ps：下面一句划重点)即使当前不存在线程阻塞在select()处，那么下一个执行select()方法的线程也会立即返回结果，相当于执行了一次selectNow()方法
            close(): 用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。channel本身并不会关闭。
      ===============》》事件
      一个Channel在Selector注册其代表的是一个SelectionKey事件，SelectionKey的类型包括：
      
          OP_READ：可读事件；值为：1<<0
          OP_WRITE：可写事件；值为：1<<2
          OP_CONNECT：客户端连接服务端的事件(tcp连接)，一般为创建SocketChannel客户端channel；值为：1<<3
          OP_ACCEPT：服务端接收客户端连接的事件，一般为创建ServerSocketChannel服务端channel；值为：1<<4
       缓冲区Buffer：
           abstract Object 	array() 	
           返回支持此缓冲区的数组 （可选操作） 。
           abstract int 	arrayOffset() 	
           返回此缓冲区缓冲区第一个元素的后备数组中的偏移量 （可选操作） 。
           int 	capacity() 	
           返回此缓冲区的容量。
           Buffer 	clear() 	
           清除此缓冲区。
           abstract Buffer 	duplicate() 	
           创建一个共享此缓冲区内容的新缓冲区。
           Buffer 	flip() 	
           翻转此缓冲区。
           abstract boolean 	hasArray() 	
           判断此缓冲区是否由可访问的数组支持。
           boolean 	hasRemaining() 	
           告知当前位置和限制之间是否存在任何元素。
           abstract boolean 	isDirect() 	
           判断此缓冲区是否为 direct 。
           abstract boolean 	isReadOnly() 	
           判断此缓冲区是否为只读。
           int 	limit() 	
           返回此缓冲区的限制。
           Buffer 	limit​(int newLimit) 	
           设置此缓冲区的限制。
           Buffer 	mark() 	
           在此位置设置此缓冲区的标记。
           int 	position() 	
           返回此缓冲区的位置。
           Buffer 	position​(int newPosition) 	
           设置此缓冲区的位置。
           int 	remaining() 	
           返回当前位置和限制之间的元素数。
           Buffer 	reset() 	
           将此缓冲区的位置重置为先前标记的位置。
           Buffer 	rewind() 	
           倒回这个缓冲区。
           abstract Buffer 	slice() 	
           创建一个新缓冲区，其内容是此缓冲区内容的共享子序列。 
       =====================》》 
          标记、位置、限制和容量值遵守以下不变式：
              0 <= mark <= position <= limit <= capacity 
          新创建的缓冲区总有一个 0 position 和一个未定义的mark 。初始limit 可以为 0，也可以为其他值，这取决于缓冲区类型及其构建方式。一般情况下，缓冲区的初始内容是未定义的。 
          
# java中的代理？
   1.jdk动态代理：
   特点：只能为interface接口生成代理类，换言之，只能代理接口
   核心接口：
   Proxy：
   InvocationHandler：
   2.cglib动态代理：
   特点：能为Class类生成代理类，换言之，可以代理类
   核心接口：
   MethodInteceptor：
   Enhancer：
   比较：
    * CGLib动态代理创建代理实例速度慢，但是运行速度快；JDK动态代理创建实例速度快，但是运行速度慢。如果实例是单例的，
    * 推荐使用CGLib方式动态代理，反之则使用JDK方式进行动态代理。Spring的实例默认是单例，所以这时候使用CGLib性能高。 
## leetcode 刷题算法总结

https://blog.csdn.net/zy450271923/article/details/105298999

