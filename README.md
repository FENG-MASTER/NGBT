# Next-Gen-Behavior-Tree #

## 简介 ##

自己实现的一个行为树库,提供了多样的控制节点,包括了

1. PrioritySelector(带优先级的选择节点)
2. NonPrioritySelector(不带优先级的选择节点)
3. WeightedSelector(带权值的选择节点)
4. Sequence(顺序选择节点)
5. <b>持续更新</b>

除了多样的控制节点外,还提供了针对不同需要的上下文环境用于传递不同作用域需求的参数

1. 全局上下文
2. 行为树级上下文
3. 节点级上下文

还提供了一套完整的配置解决方案(json格式)


## 进度 ##

基本完成了初期版本的开发,正在整理使用文档.

# 使用说明 #

>**可以直接参考 test包下的例子**

## 1. 添加依赖包 ##
把目录jar里的jar包拷贝到你的java工程目录下,并添加为依赖包即可

## 2. 添加相关代码 ##

### 2.1新建自己的动作节点 ###

所有自定义的动作节点必须继承 **AbsActionNode**

如test包给出的例子一样

	public class EatAction extends AbsActionNode {
	    @Override
	    public void execute(Context context) {
	        String meat = (String) context.getTreeContext().get("meat");
	        if (meat==null){
	            return;
	        }
	        System.out.println("宠物吃掉了"+meat);
	    }
	
	    @Override
	    public boolean condition(Context context) {
	        String meat = (String) context.getTreeContext().get("meat");
	        if (meat==null){
	            return false;
	        }
	        return true;
	    }
	
	    @Override
	    public State getState(Context context) {
	        return State.STOP;
	    }
	
	    @Override
	    public void init(JSONObject conf) throws JSONException {
	
	    }
	}

这是一个简单的动作节点

### 2.2初始化loader ###

由于采用了可供配置的行为树,需要动态生成节点,这里必须把自定义的动作节点所在包名添加到loader中,形如(可添加多个包名):

    //必须添加配置文件中使用的类所在包名
    NodeLoader.getInstance().addPackageName("com.fengmaster.ngbt.test.action");

然后调用

    //初始化
    NodeLoader.getInstance().init();

即完成了初始化

### 2.3配置行为树 ###
NodeLoader.getInstance().init()函数中可以传入配置文件目录,否则默认为当前目录下的conf文件夹

打开配置文件夹,新建形如 **XXXX.conf** 的配置文本文件,XXXX为程序中使用的行为树唯一标识名称

暂时只支持json格式的配置文件

以下是一个简单的例子:

	{
		"class": "NonPrioritySelector",
		"leafs": [{
			"class": "WeightedSelector",
			"leafs": [{
				"class": "EatAction",
				"weight": 2
			},
			{
				"class": "FeedAction",
				"childrenNum": 5,
				"weight": 1
			},
			{
				"class": "IgnoreAction",
				"weight": 1
			}]
		}]
	}

**详细的选择节点在文档后面有说明**

### 2.4 使用行为树 ###

	INode petAi = NodePool.getInstance().getNode("Pet");

这样就获得了我们配置好的pet.conf对应的行为树了.

	//new一个上下文对象,用于传递参数给行为树节点
    Context context=new Context();
	petAi.execute(context);

就可以直接执行了

具体怎么传递参数,后面也有说明




## 选择节点说明 ##

**PS:所有选择节点下的叶子节点都需要配置到leafs对应的json数组中**

目前支持的选择节点有:

----------

### Sequence ###

**顺序选择节点**:

#### 进入条件 #### 

所有子节点的进入条件都满足

#### 执行动作 ####

所有节点一起执行

#### 节点状态 ####

两种模式,配置方法是 对应的 type字段:

	{
	 "class":"Sequence",
	  "type":AND
	}

AND:这个模式下,只有当所有叶子节点的状态都是STOP的时候,才会返回STOP状态,否则返回RUNNING状态
OR: 这个模式下,只要有一个叶子节点的状态是STOP,则返回STOP

----------


### PrioritySelector ###

**带优先级的选择节点**

#### 进入条件 ####

按顺序从第一个子节点开始,找到第一个满足条件的节点则执行相应子节点

#### 执行动作 ####

执行相应子节点

#### 节点状态 ####

节点状态完全和找到的满足条件的子节点状态相同


----------

### NonPrioritySelector ###

**不带优先级的选择节点**

#### 进入条件 ####

从**上次找到符合条件的子节点**(如果没有则从第一个)开始,找到第一个满足条件的节点则执行相应子节点

#### 执行动作 ####

执行相应子节点

#### 节点状态 ####

节点状态完全和找到的满足条件的子节点状态相同



----------


### WeightedSelector ###

**带权值的选择节点**

这个选择节点可以实现一些**随机的AI**行为

PS:所有子节点可以配置相应的权重值,这个值影响到了随机选中这个子节点的几率

配置形如:

	{
		"class": "WeightedSelector",
		"leafs": [{
			"class": "EatAction",
			"weight": 2
		},
		{
			"class": "FeedAction",
			"childrenNum": 5,
			"weight": 1
		}]
	}

#### 进入条件 ####

随机选择一个节点(权重值表示了选中这个节点所占比),如果这个节点满足条件,则执行,如果不满足,继续随机选择其他节点

#### 执行动作 ####

执行相应子节点

#### 节点状态 ####

节点状态完全和找到的满足条件的子节点状态相同


## 传递参数 ##

大部分时候,你都会需要向行为树中的节点传递参数

这里提供了三种作用域的参数传递方式

首先都需要**新建一个上下文对象**:

	Context context=new Context();

### 全局上下文 ###

全局上下文对象里的所有变量,是所有行为树共享的,即只有一个全局上下文对象

**用于多个行为树之间传递变量**

#### 添加变量 ####

	context.getGlobalContext().put("key","val");

传入一个键值对即可,key类型为Object,val类型也是Object

#### 获得变量 ####

     context.getGlobalContext().get("key");

#### 移除变量 ####

     context.getGlobalContext().remove("key");

### 行为树级上下文 ###

行为树级上下文对象,只能在本行为树中使用,具体的说,一个Context对象包含了一个行为树级上下文对象,使用方式上和全局上下文一样

#### 添加变量 ####

	context.getTreeContext().put("key","val");

传入一个键值对即可,key类型为Object,val类型也是Object

#### 获得变量 ####

     context.getTreeContext().get("key");

#### 移除变量 ####

     context.getTreeContext().remove("key");

### 节点级上下文 ###

节点级上下文对象,只能某个指定节点才能获得相应的变量,用于指定节点直接的参数传递

#### 添加变量 ####

	context.getNodeContext().put("nodeName","key","val");

第一个参数为指定的节点类名,key类型为Object,val类型也是Object,这样我们就传入了一个只能由特定节点获得的变量

#### 获得变量 ####

要获得节点级上下文的变量,必须在节点中执行

    context.getNodeContext().get(this,"key");

第一个参数是一个INode对象,一般传入自身,因为我们要获得是自己所能得到的变量

PS:这里不是强制直接传入this,这只是一种规约

#### 移除变量 ####

     context.getNodeContext().remove(this,"key");
     context.getNodeContext().remove("nodeName","key");

以上两种方式都可以移除节点级上下文中的变量


