package com.woniu.spring.aop.aspectj;

import com.woniu.spring.PointcutAdvisor;
import com.woniu.spring.aop.Pointcut;
import org.aopalliance.aop.Advice;

/**
 * @Author: zhangcheng
 * @CreateTime: 2022-09-29  17:11
 * @Description:
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}
