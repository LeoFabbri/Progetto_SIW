let songsCont = 1;

            function addSong() {
            fetch('/rest/songsWithoutAlbum')
                .then(response => response.json())
                .then(data => {

                    if (Array.isArray(data) && data.length > 0) {
                        const div = document.createElement('div');
                        div.className = 'd-flex align-items-center mb-2 justify-content-center';

                        const select = document.createElement('select');
                        select.className = 'form-control mb-2 custom-width';
                        select.id = 'songId_' + songsCont;
                        select.name = 'songsId[' + songsCont + ']';

                        const button = document.createElement('input')
                        button.id = 'songButton_' + songsCont;
                        button.type = 'button';
                        button.value = 'Rimuovi';
                        button.className = 'btn btn-danger btn-sm';
                        button.setAttribute('onclick', 'removeSong(' + songsCont + ')');

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
                        songsCont++;
                    } else {
                        console.error('No songs found or data is not an array');
                    }
                })
                .catch(error => console.error('Error fetching songs:', error));
            }

            function removeSong(id) {
                document.getElementById('songId_' + id).parentNode.remove();
            }