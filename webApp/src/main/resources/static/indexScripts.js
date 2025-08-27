const responseField = document.getElementById("responseField");


async function test() {
    const response = await fetch("http://192.168.0.106:8080/api/test", {
        method: 'GET'
    }
    );
    try{
        if (!response.ok) throw new Error('Ошибка сервера');

        const result = await response.text();
        responseField.textContent = result;

    } catch (err){
        console.log(err);
    }
    
}