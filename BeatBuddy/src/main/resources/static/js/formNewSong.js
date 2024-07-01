let singersCont = 0;
let writersCont = 0;
let producersCont = 0;

            function addSingers() {

                fetch('/rest/singers').then(data => data.json()).then(data => {
                    const div = document.createElement('div');
                    div.className = 'd-flex align-items-center mb-2 justify-content-center';

                    const select = document.createElement('select');
                    select.className = 'form-control mb-2 custom-width';
                    select.id = 'singerId_' + singersCont;
                    select.name = 'singersId[' + singersCont + ']';

                    const button = document.createElement('input')
                    button.id = 'singerButton_' + singersCont;
                    button.type = 'button';
                    button.value = 'Rimuovi';
                    button.className = 'btn btn-danger btn-sm';
                    button.setAttribute('onclick', 'removeSinger(' + singersCont + ')');
                    data.forEach(item => {
                        if(typeof item === 'object' && item !== null) {
                            const option = document.createElement('option');
                            option.value = item.id;
                            option.textContent = item.stageName;
                            select.appendChild(option);
                        }else{
                            fetch('/rest/artists/'+item).then(data => data.json()).then(data => {
                            const option = document.createElement('option');
                            option.value = data.id;
                            option.textContent = data.stageName;
                            select.appendChild(option);
                            });
                        }
                    });
                    div.appendChild(select);
                    div.appendChild(button);
                    document.getElementById('singersDiv').appendChild(div);
                    singersCont++;
                }).catch(error => console.error('Error fetching singers:', error));

            }

            function addWriters() {

                fetch('/rest/artists').then(data => data.json()).then(data => {
                    const div = document.createElement('div');
                    div.className = 'd-flex align-items-center mb-2 justify-content-center';

                    const select = document.createElement('select');
                    select.className = 'form-control mb-2 custom-width';
                    select.id = 'writerId_' + writersCont;
                    select.name = 'writersId[' + writersCont + ']';

                    const button = document.createElement('input')
                    button.id = 'writerButton_' + writersCont;
                    button.type = 'button';
                    button.value = 'Rimuovi';
                    button.className = 'btn btn-danger btn-sm';
                    button.setAttribute('onclick', 'removeWriter(' + writersCont + ')');
                    data.forEach(item => {
                        if(typeof item === 'object' && item !== null) {
                            const option = document.createElement('option');
                            option.value = item.id;
                            option.textContent = item.stageName;
                            select.appendChild(option);
                        }else{
                            fetch('/rest/artists/'+item).then(data => data.json()).then(data => {
                            const option = document.createElement('option');
                            option.value = data.id;
                            option.textContent = data.stageName;
                            select.appendChild(option);
                            });
                        }
                    });
                    div.appendChild(select);
                    div.appendChild(button);
                    document.getElementById('writersDiv').appendChild(div);
                    writersCont++;
                }).catch(error => console.error('Error fetching writers:', error));

            }

            function addProducers() {

                fetch('/rest/singers').then(data => data.json()).then(data => {
                    const div = document.createElement('div');
                    div.className = 'd-flex align-items-center mb-2 justify-content-center';

                    const select = document.createElement('select');
                    select.className = 'form-control mb-2 custom-width';
                    select.id = 'producerId_' + producersCont;
                    select.name = 'producersId[' + producersCont + ']';

                    const button = document.createElement('input')
                    button.id = 'producerButton_' + producersCont;
                    button.type = 'button';
                    button.value = 'Rimuovi';
                    button.className = 'btn btn-danger btn-sm';
                    button.setAttribute('onclick', 'removeProducer(' + producersCont + ')');
                    data.forEach(item => {
                        if(typeof item === 'object' && item !== null) {
                            const option = document.createElement('option');
                            option.value = item.id;
                            option.textContent = item.stageName;
                            select.appendChild(option);
                        }else{
                            fetch('/rest/artists/'+item).then(data => data.json()).then(data => {
                            const option = document.createElement('option');
                            option.value = data.id;
                            option.textContent = data.stageName;
                            select.appendChild(option);
                            });
                        }
                    });
                    div.appendChild(select);
                    div.appendChild(button);
                    document.getElementById('producersDiv').appendChild(div);
                    producersCont++;
                }).catch(error => console.error('Error fetching producers:', error));

            }

            function removeSinger(id) {
                document.getElementById('singerId_' + id).parentNode.remove();
            }

            function removeWriter(id) {
                document.getElementById('writerId_' + id).parentNode.remove();
            }

            function removeProducer(id) {
                document.getElementById('producerId_' + id).parentNode.remove();
            }