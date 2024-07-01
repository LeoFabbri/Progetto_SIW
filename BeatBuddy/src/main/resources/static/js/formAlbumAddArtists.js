let artistsCont = 1;

            function addArtists() {
            fetch('/rest/singers').then(data => data.json()).then(data => {
                const div = document.createElement('div');
                div.className = 'd-flex align-items-center mb-2';

                const select = document.createElement('select');
                select.className = 'form-control mb-2 custom-width mx-auto';
                select.id = 'artistId_' + artistsCont;
                select.name = 'artistsId[' + artistsCont + ']';

                const button = document.createElement('input')
                button.id = 'artistButton_' + artistsCont;
                button.type = 'button';
                button.value = 'Rimuovi';
                button.className = 'btn btn-danger btn-sm';
                button.setAttribute('onclick', 'removeArtist(' + artistsCont + ')');

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
                document.getElementById('artistsDiv').appendChild(div);
                artistsCont++;
            }).catch(error => console.error('Error fetching artists:', error));
        }

        function removeArtist(id) {
            document.getElementById('artistId_' + id).parentNode.remove();
        }
