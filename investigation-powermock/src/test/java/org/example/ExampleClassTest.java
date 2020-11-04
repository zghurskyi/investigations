package org.example;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExampleClass.class)
public class ExampleClassTest {

    private final ExampleClass exampleClass = new ExampleClass();

    @Test
    public void exceptionIsNotThrownIfCoolObjHasNoErrors() throws Exception {
        CoolObj coolObjMock = Mockito.mock(CoolObj.class);
        PowerMockito.whenNew(CoolObj.class).withNoArguments().thenReturn(coolObjMock);
        Mockito.when(coolObjMock.hasErrors()).thenReturn(false);

        Assertions.assertThatCode(exampleClass::doSomething)
                .doesNotThrowAnyException();
    }

    @Test
    public void exceptionIsThrownIfCoolObjHasErrors() throws Exception {
        CoolObj coolObjMock = Mockito.mock(CoolObj.class);
        PowerMockito.whenNew(CoolObj.class).withNoArguments().thenReturn(coolObjMock);
        Mockito.when(coolObjMock.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(exampleClass::doSomething)
                .isInstanceOf(RuntimeException.class);
    }
}

class ExampleClass {

    public void doSomething() {
        CoolObj coolObj = new CoolObj();

        verify(coolObj);
    }

    private void verify(CoolObj coolObj) {
        if (coolObj.hasErrors()) {
            throw new RuntimeException(); //this is my issue
        }
    }
}

class CoolObj {
    public boolean hasErrors() {
        return false;
    }
}