//////////////////////////////// 함수화 //////////////////////////////////////////

let currentObserver = null;

const observe = fn => {
    currentObserver = fn;
    fn();
    currentObserver = null;
}

const observable = obj => {
    Object.keys(obj).forEach(key => {
        let _value = obj[key]
        const observers = new Set();

        Object.defineProperty(obj,key,{
            get(){
                if(currentObserver) observers.add(currentObserver);
                return _value;
            },

            set(value){
                _value = value;
                observers.forEach(fn => fn());
            }
        })
    })
    return obj;
}

const store = observable({a : 10, b : 20});
observe(() => console.log(`a = ${store.a}`)); // a = 10
observe(() => console.log(`b = ${store.b}`)); // b = 20
observe(() => console.log(`a + b = ${store.a + store.b}`)); // a + b = 30
observe(() => console.log(`a - b = ${store.a - store.b}`)); // a - b = -10

store.a = 100; // a = 100, a + b = 120, a - b = 80
store.b = 200; // b = 200, a + b = 300, a - b = -100