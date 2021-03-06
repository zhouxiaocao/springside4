package org.springside.modules.utils.reflect;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.Test;

public class ClassesTest {

	public interface AInterface {
	}

	@CAnnotation
	public interface BInterface extends AInterface {
		@FAnnotation
		void hello();
	}

	public interface CInterface {
	}

	public interface DInterface {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface AAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@AAnnotation
	public @interface BAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface CAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface DAnnotation {
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface EAnnotation {
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface FAnnotation {
	}

	@DAnnotation
	public static class AClass implements DInterface {
	}

	@BAnnotation
	public static class BClass extends AClass implements CInterface, BInterface {

		@Override
		@EAnnotation
		public void hello() {
			// TODO Auto-generated method stub
			
		}

	}

	@Test
	public  void getAllMessage() {
		System.out.println(Classes.getAllInterfaces(BClass.class));
		System.out.println(Classes.getAllSuperclasses(BClass.class));
		System.out.println(Classes.getAllAnnotations(BClass.class));
		System.out.println(Classes.getMethodsAnnotatedWith(BClass.class, EAnnotation.class));
		System.out.println(Classes.getMethodsAnnotatedWith(BClass.class, FAnnotation.class));
		System.out.println(Classes.getMethodsAnnotatedWith(BClass.class, AAnnotation.class));
	}

	@Test
	public void getSuperClassGenricType() {
		// 获取第1，2个泛型类型
		assertThat(Classes.getClassGenricType(TestBean.class)).isEqualTo(String.class);
		assertThat(Classes.getClassGenricType(TestBean.class, 1)).isEqualTo(Long.class);

		// 定义父类时无泛型定义
		assertThat(Classes.getClassGenricType(TestBean2.class)).isEqualTo(Object.class);

		// 无父类定义
		assertThat(Classes.getClassGenricType(TestBean3.class)).isEqualTo(Object.class);
	}

	public static class ParentBean<T, ID> {
	}

	public static class TestBean extends ParentBean<String, Long> {

	}

	public static class TestBean2 extends ParentBean {
	}

	public static class TestBean3 {
	
	}

}
