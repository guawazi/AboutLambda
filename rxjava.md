## 使用lambda 

1. 在根build.gradle 中

		dependencies {
	        classpath 'com.android.tools.build:gradle:2.3.2'
	        classpath 'me.tatarka:gradle-retrolambda:3.2.0'
   		 }

2. app build.gradle 中

		apply plugin: 'me.tatarka.retrolambda'

		android{
			...
			 compileOptions {
			        sourceCompatibility JavaVersion.VERSION_1_8
			        targetCompatibility JavaVersion.VERSION_1_8
			    }
			...
			}

3. 简单使用

		 btn_1 = (Button) findViewById(R.id.btn_1);
		 btn_1.setOnClickListener(v -> Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show());


