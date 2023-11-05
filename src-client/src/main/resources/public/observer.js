class Pub{
    construtor(state){
        this.observers = new Set();
        this.state = state;
        Object.keys(state).forEach(key => Object.defineProperty(this, key, {
            get : () => this.state[key]
        }));
    }

    setState(newState){
        this.state = { ...this.state, ...newState};
        this.notifyAll();
    }

    register(subscriber){
        this.observers.add(subscriber);
    }

    notifyAll(){
        this.observers.forEach(fn => fn());
    }
}


class Sub{
    constructor(fn){
        this.fn = fn; // 생성될때 주입되는 프로퍼티
    }

    subscribe(Pub){
        Pub.register(this.fn);
    }
}

const store = new Pub({
    a : 10,
    b : 20,
}) // a, b 속성을 가진 state를 가진 Pub 객체

const addCal = new Sub(() => console.log(store.a + store.b)); // console함수가 주입되면서 생성
const minusCal = new Sub(() => console.log(store.a - store.b));

addCal.subscribe(store);
minusCal.subscribe(store); // observers에 console 등록

store.notifyAll();  // observers 순회 -> 30, -10

store.setState({ a : 100, b : 20}); // 120, 80






