let songsCont = 0;
        let existingSongsCont = 0;
        let artistsCont = 0;

        function addArtists() {
            fetch('/rest/singers').then(data => data.json()).then(data => {
                const div = document.createElement('div');
                div.className = 'd-flex align-items-center mb-2 justify-content-center';

                const select = document.createElement('select');
                select.className = 'form-control mb-2 custom-width';
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

        function addSong() {
            fetch('/rest/songsWithoutAlbum')
                .then(response => response.json())
                .then(data => {

                    if (Array.isArray(data) && data.length > 0) {
                        const div = document.createElement('div');
                        div.className = 'd-flex align-items-center mb-2 justify-content-center';

                        const select = document.createElement('select');
                        select.className = 'form-control mb-2 custom-width';
                        select.id = 'songId_' + existingSongsCont;
                        select.name = 'songsId[' + existingSongsCont + ']';

                        const button = document.createElement('input')
                        button.id = 'songButton_' + existingSongsCont;
                        button.type = 'button';
                        button.value = 'Rimuovi';
                        button.className = 'btn btn-danger btn-sm';
                        button.setAttribute('onclick', 'removeSong(' + existingSongsCont + ')');

                        data.forEach(item => {
                            if (typeof item === 'object' && item !== null) {
                                const option = document.createElement('option');
                                option.value = item.id;
                                option.textContent = item.title;
                                select.appendChild(option);
                            }else{
                                fetch('/rest/songs/'+item).then(data => data.json()).then(data => {
                                    const option = document.createElement('option');
                                    option.value = data.id;
                                    option.textContent = data.title;
                                    select.appendChild(option);
                                });
                            }
                        });
                        div.appendChild(select);
                        div.appendChild(button)
                        document.getElementById('songsDiv').appendChild(div);
                        existingSongsCont++;
                    } else {
                        console.error('No songs found or data is not an array');
                    }
                })
                .catch(error => console.error('Error fetching songs:', error));
        }

        function removeSong(id) {
            document.getElementById('songId_' + id).parentNode.remove();
        }