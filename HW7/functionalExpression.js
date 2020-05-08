"use strict";

function UnaryOperation(exp, f, tostr) {
    this.exp = exp;
    this.evaluate = f;
    this.toString = value => x => value;
    this.toString = tostr;
}

function BinaryOperation(a, b, f, sign) {
    this.expl = a;
    this.expr = b;
    this.evaluate = f;
    this.sign = sign;
    this.toString = function(a, b) {
        return a.toString() + b.toString() + sign + " ";
    };

}

const abstractOperation = operation => (...operands) => (...values) => {
    let result = [];
    for (let operand of operands) {
        result.push(operand(...values));
    }
    return operation(...result);
}


const sinh = abstractOperation(a => Math.sinh(a));
const cosh = abstractOperation(a => Math.cosh(a));


function Const(val) {
    UnaryOperation.call(this, val, value => x => parseInt(value, 10), value => x => value);
}

function Variable(val) {
    UnaryOperation.call(this, val,
        name => {
            let ind = (name === "x") ? 0 : (name === "y") ? 1 : 2;
            return (...values) => values[ind];
        },
        value => x => value);
}

function negate(val) {
    UnaryOperation.call(this, val, value => x => -parseInt(value, 10), value => x => value + " - ");
}
function Add(a, b) {
    BinaryOperation.call(this, a, b, abstractOperation((a, b) => a + b), '+');
}
function Subtract(a, b) {
    BinaryOperation.call(this, a, b, abstractOperation((a, b) => a - b), '-');
}
function Multiply(a, b) {
    BinaryOperation.call(this, a, b, abstractOperation((a, b) => a * b), '*');
}
function Divide(a, b) {
    BinaryOperation.call(this, a, b, abstractOperation((a, b) => a / b), '/');
}
