<?php


defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . '/libraries/Format.php';

class Program extends REST_Controller {

    // Konfigurasi letak folder untuk upload image

    function all_get(){
        $get_program = $this->db->query("
            SELECT
                id_program,
                nama_program,
                type,
                kategori,
                tempat,
                tanggal,
                link,
                biaya_daftar
            FROM program order by id_program asc")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_program
           )
       );
    }

    function all_post() {

        $action  = $this->post('action');
        $data_program = array(
                        'id_program' => $this->post('id_program'),
                        'nama_program' => $this->post('nama_program'),
                        'type' => $this->post('type'),
                        'kategori' => $this->post('kategori'),
                        'tempat' => $this->post('tempat'),
                        'tanggal' => $this->post('tanggal'),
                        'link' => $this->post('link'),
                        'biaya_daftar' => $this->post('biaya_daftar'),
                    );

        switch ($action) {
            case 'insert':
                $this->insertProgram($data_program);
                break;
            
            case 'update':
                $this->updateProgram($data_program);
                break;
            
            case 'delete':
                $this->deleteProgram($data_program);
                break;
            
            default:
                $this->response(
                    array(
                        "status"  =>"failed",
                        "message" => "action harus diisi"
                    )
                );
                break;
        }
    }

    function insertProgram($data_program){
        if (empty($data_program['nama_program'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "Nama Program harus diisi"
                )
            );
        } else {

            $do_insert = $this->db->insert('program', $data_program);
           
            if ($do_insert){
                $this->response(
                    array(
                        "status" => "success",
                        "result" => array($data_program),
                        "message" => $do_insert
                    )
                );
            }
        }
    }

    function updateProgram($data_program){

        // Cek validasi
        if (empty($data_program['nama_program'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "Nama Program harus diisi"
                )
            );
        } else {
            // Cek apakah ada di database
            $get_program_baseID = $this->db->query("
                SELECT 1 id_program
                FROM program
                WHERE id_program =  {$data_program['id_program']}")->num_rows();

            if($get_program_baseID === 0){
                // Jika tidak ada
                $this->response(
                    array(
                        "status"  => "failed",
                        "message" => "ID program tidak ditemukan"
                    )
                );
            } else {
            
              
                    $update = $this->db->query("
                        UPDATE program
                        SET
                        nama_program    = '{$data_program['nama_program']}',
                        type    = '{$data_program['type']}',
                        kategori    = '{$data_program['kategori']}',
                        tempat    = '{$data_program['tempat']}',
                        tanggal    = '{$data_program['tanggal']}',
                        link    = '{$data_program['link']}',
                        biaya_daftar    = '{$data_program['biaya_daftar']}'
                        WHERE id_program = {$data_program['id_program']}"
                    );
               
                if ($update){
                    $this->response(
                        array(
                            "status"    => "success",
                            "result"    => array($data_program),
                            "message"   => $update
                        )
                    );
                }
            }   
        }
    }

    function deleteProgram($data_program){

        if (empty($data_program['id_program'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "ID Pembeli harus diisi"
                )
            );
        } else {
            // Cek apakah ada di database
            $get_program_baseID = $this->db->query("
            SELECT 1 id_program
            FROM program
            WHERE id_program =  {$data_program['id_program']}")->num_rows();


            if($get_program_baseID > 0){
                
              
                    $this->db->query("
                        DELETE FROM program
                        WHERE id_program = {$data_program['id_program']}");
                    $this->response(
                        array(
                            "status" => "success",
                            "message" => "Data ID = " .$data_program['id_program']. " berhasil dihapus"
                        )
                    );
                
            
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID User tidak ditemukan"
                    )
                );
            }
        }
    }


}
