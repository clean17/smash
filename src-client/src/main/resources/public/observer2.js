/////////////////////////// 클로저 + 여러 옵저버 /////////////////////////////////

let currentObserver = null; // 활성화된 옵저버

const state = {
    a : 10,
    b : 20,
};

const stateKeys = Object.keys(state);

for( const key of stateKeys ) {
    // 클로저를 통해 접근가능한 private 변수
    let _value = state[key];
    const observers = new Set();

    // 모든 key에 getter, setter를 정의
    Object.defineProperty(state, key, {
        get(){
            if ( currentObserver ) observers.add(currentObserver);
            return _value;
        },
        set(value){
            _value = value;
            observers.forEach(observer => observer());
        }
    })
}

const addCal = () => {
    currentObserver = addCal;
    console.log(`a+b = ${state.a + state.b}`);
}

const minusCal = () => {
    currentObserver = minusCal;
    console.log(`a - b = ${state.a - state.b}`);
}

addCal(); // a+b = 30, 옵저버에 addCal() 추가
state.a = 100;
// 1. 클로저를 통해 _value를 변경
// 2. 등록된 옵저버 실행 (addCal)
// 3. a+b = 120 출력

minusCal(); // a-b = 80, 옵저버에 minusCal() 추가
state.b = 200;
// 1. 클로저를 통해 _value를 변경
// 2. 등록된 옵저버 실행 (addCal, minusCal)
// 3. a+b = 300, a-b = -100 출력

state.a = 1;
// a+b = 201, a-b = -199

state.b = 2;
// a+b = 3, a-b = -1